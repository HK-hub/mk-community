package com.hk.community.controller;

import com.hk.community.service.serviceImp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 31618
 * @description
 * @date 2021-04-06 18:26
 */
@Controller
public class LoginController {

	@Autowired
	private UserServiceImp userServiceImp ;


	@GetMapping("/login")   //处理前端登录按钮传来的登录请求
	public String login(){

		return "signIn" ;
	}

	//用户进入登录界面, 输入账号密码, 进行登录
	@PostMapping("/userLogin")
	public String userLogin(@RequestParam(name = "userAccount")String userAccount ,
	                        @RequestParam(name = "password")String password,
							HttpServletResponse response ,
                            Model model
	){
		final String msg = userServiceImp.verifyLoginUser(userAccount, password, response);
		if("login failed".equals(msg)){
			model.addAttribute("userLoginMsg","login failed: account or password error") ;
			return "signIn" ;
		}
		else if ("login success".equals(msg)){
			//response.addCookie(new Cookie("user_token", ));
			model.addAttribute("userLoginMsg", "login success") ;
		}
		return "redirect:/";
	}

	//用户在登录界面点击注册进入注册界面
	@RequestMapping("/register")
	public String register(){

		return "signUp" ;
	}

	//用户在注册页面, 提交注册请求
	@PostMapping("/user-register")
	public String userRigister(@RequestParam(name = "Name")String name ,
                               @RequestParam(name = "Email")String email ,
                               @RequestParam(name = "Password")String passwdord,
                               Model model
	                           ){
		System.out.println(name);
		System.out.println(email);
		System.out.println(passwdord);
		String msg = userServiceImp.addLoginRegisterUser(name, email, passwdord);
		if ("user already exist".equals(msg)){
			//用户已经存在
			model.addAttribute("userRegistryMsg", "用户已经存在,请进行登录");
			System.out.println("用户已经存在,请进行登录");
			return "signUp" ;
		}
		else {
			System.out.println("注册成功");
		}
		return "signIn" ;
	}

	
	//用户退出登录
	@RequestMapping("/sign-out")
	public String signOut(HttpServletRequest request){

		userServiceImp.loginOut(request) ;
		return "redirect:/login";
	}
	
	
	
	

}
