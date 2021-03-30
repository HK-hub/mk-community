package com.hk.community.controller;

import com.hk.community.mapper.UserMapper;
import com.hk.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 31618
 * @description
 * @date 2021-03-23 17:57
 */
@Controller
public class IndexController {

	@Autowired  //注入 UserMapper .来进行访问User数据库
	private UserMapper userMapper ;

	@RequestMapping("/")    //GetMapping
	public String index(HttpServletRequest request){

		String token = null;

		//获取Cookie
		Cookie[] cookies = request.getCookies();
		if (cookies != null){
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user_token")){
					token = cookie.getValue() ;
					break;
				}
			}
		}


		if (token != null){
			final User user = userMapper.findByToken(token);
			if (user != null){
				request.getSession().setAttribute("user",user);
			}
		}

		return "index" ;
	}

}
