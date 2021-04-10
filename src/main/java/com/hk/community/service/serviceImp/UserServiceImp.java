package com.hk.community.service.serviceImp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hk.community.mapper.UserMapper;
import com.hk.community.model.User;
import com.hk.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author 31618
 * @description
 * @date 2021-04-02 13:38
 */
@Service
public class UserServiceImp extends ServiceImpl<UserMapper, User> implements UserService {

	@Autowired
	private UserMapper userMapper ;

	//根据 Token 获取User 对象
	public User findUserByToken(String token){

		User user = userMapper.findByToken(token);
		return user ;
	}

	//插入对象

	//处理登录注册的用户对象
	public String addLoginRegisterUser(String name , String email , String password){

		if (userMapper.findByEmail(email) != null){
			return "user already exist" ;
		}
		User user = new User();
		//设置关键信息
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		user.setAccount_id("cc:"+System.currentTimeMillis());
		user.setToken(UUID.randomUUID().toString());

		//设置个人信息：描述， 默认头像
		user.setAvatar_url("https://iconfont.alicdn.com/t/b219af17-ee89-4bad-b48a-7d6c1147009d.png");
		user.setBio("a plain coder");
		user.setCreate_time(System.currentTimeMillis());
		user.setModified_time(user.getCreate_time());
		userMapper.insert(user) ;
		return "success register";
	}

	//判断登录用户的账户名和密码
	public String verifyLoginUser(String email , String password,
	                              HttpServletResponse response){
		String msg ;
		final User byEmail = userMapper.findByEmail(email);
		if ( byEmail != null && byEmail.getPassword().equals(password)){
			response.addCookie(new Cookie("user_token", byEmail.getToken()));
			msg = "login success" ;
		}
		else {
			msg = "login failed" ;
		}
		return msg ;
	}


	public void loginOut(HttpServletRequest request) {
		HttpSession userSession = request.getSession();
		//清除userSession 中的值
		userSession.removeAttribute("user");

		//销毁userSession 对象
		//userSession.invalidate();
	}
}
