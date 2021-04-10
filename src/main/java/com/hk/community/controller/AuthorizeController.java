package com.hk.community.controller;

import com.hk.community.dto.AccessTokenDTO;
import com.hk.community.dto.GithubUser;
import com.hk.community.mapper.UserMapper;
import com.hk.community.model.User;
import com.hk.community.provider.GithubProvider;
import com.hk.community.utils.HttpHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author 31618
 * @description
 * @date 2021-03-26 19:42
 */
@Controller
public class AuthorizeController {


	@Autowired
	private HttpHelper httpHelper;

	@Autowired  //自动注入
	UserMapper userMapper;

	@Autowired
	private GithubProvider githubProvider ;
	//属性注入
	@Value("${github.client.id}")
	private String clientId ;
	@Value("${github.client.secret}")
	private String clientSecret ;
	@Value("${github.redirect.uri}")
	private String redirectURL ;




	@GetMapping("/callback")
	public String callback(@RequestParam(name = "code")String code,
	                       @RequestParam(name = "state")String state,
	                       HttpServletRequest request,
	                       HttpServletResponse response){

		AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
		//: 重定向回站点, 使用 code 换令牌
		accessTokenDTO.setCode(code);
		accessTokenDTO.setRedirect_url(redirectURL);
		accessTokenDTO.setState(state);
		accessTokenDTO.setClient_id(clientId);
		accessTokenDTO.setClient_secret(clientSecret);
		//获取 access_token
		String token = githubProvider.getAccessToken(accessTokenDTO);
		//根据 access_token 获取 User 对象
		GithubUser githubUser = githubProvider.getUser(token);

		System.out.println(githubUser.getName());


		//登录判断
		if (githubUser != null){
			//登录成功: 将用户存储进入数据库
			User user = new User();
			String user_token =  UUID.randomUUID().toString();
			user.setToken(user_token);
			user.setName(githubUser.getName());
			user.setAccount_id(String.valueOf(githubUser.getId()));
			user.setCreate_time(System.currentTimeMillis());
			user.setModified_time(user.getCreate_time());
			//设置用户头像
			user.setAvatar_url(githubUser.getAvatarUrl());
			//插入用户
			user.setEmail(githubUser.getEmail());
			user.setPassword("mk520cc");
			userMapper.insertUser(user);
			//登录成功 : 写 cookie 和 session
			response.addCookie(new Cookie("user_token", user_token));
			//进行重定向: 请求访问地址改变
			System.out.println("用户名: "+githubUser.getName());
			return "redirect:/" ;
		}else
		{
			//登录失败
			System.out.println("没有用户");
			return "redirect:/";
		}
	}


}
