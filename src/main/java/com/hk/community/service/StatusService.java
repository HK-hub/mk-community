package com.hk.community.service;


import com.hk.community.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 31618
 * @description : 描述项目数据, 逐渐的状态
 * @date 2021-04-09 13:47
 */
//基本Service 接口: 不继承Mapper, 和IService
public interface StatusService {

	//用户登录状态检测
	public static int userLoginStatus(HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("user");
		return user==null ? 0 : 1;
	}


	//用户存在状态检测
	public static int userExitStatus() {
		return 0;
	}

	//文章问题状态



}