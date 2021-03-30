package com.hk.community.service;

import com.hk.community.model.User;

/**
 * @author 31618
 * @description : 用户service 服务
 * @date 2021-03-30 8:47
 */
public interface UserService {

	//查找
	public User findUserById();
	//插入
	public void insertUser() ;
	//删除
	public void deleteUser() ;
	//更新
	public void updateUser() ;



}