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

	@Autowired
	private GithubProvider githubProvider ;
	//属性注入
	@Value("${github.client.id}")
	private String clientId ;
	@Value("${github.client.secret}")
	private String clientSecret ;
	@Value("${github.redirect.uri}")
	private String redirectURL ;

	@Autowired  //自动注入
	UserMapper userMapper;


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
			userMapper.insertUser(user);    //插入用户

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








	//	@GetMapping("/callback")
//	@ResponseBody
//	public String callback(@RequestParam(name = "code")String code,
//	                        @RequestParam(name = "state")String state,
//	                        HttpServletRequest request){
//
//		//获取 JSON
//		Map<String,Object> map = new HashMap<>();
//		map.put("client_id",clientId);
//		map.put("client_secret",clientSecret);
//		map.put("code",code);
//		map.put("redirect_url",redirectURL);
//		map.put("state", state);
//		//获取access token
//		String url = "https://github.com/login/oauth/access_token";
//		String json = JSON.toJSONString(map);
//		//2.根据传入的参数（包含code），post请求https://github.com/login/oauth/access_token，获取返回值
//		String result = httpHelper.Post(url, json);//access_token=your_client_id&scope=user&token_type=bearer
//		System.out.println("callback result:" + result);
//
//		String[] strs = result.split("&");
//		String access_token = strs[0].split("=")[1];//解析access_token
//
//		String url_user = "https://api.github.com/user?access_token=" + access_token;
//		String userInfo = httpHelper.Get(url_user);
//		System.out.println("userInfo:" + userInfo);//返回的是一个json字符串
//
//		return userInfo ;
//	}

}
