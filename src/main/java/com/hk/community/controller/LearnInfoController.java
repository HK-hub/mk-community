package com.hk.community.controller;

import com.hk.community.service.serviceImp.BookServiceImpl;
import com.hk.community.service.serviceImp.VideoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author 31618
 * @description : 书籍, 视频
 * @date 2021-04-19 18:50
 */
@Controller
public class LearnInfoController {
	
	@Autowired
	private VideoServiceImpl videoService ;
	@Autowired
	private BookServiceImpl bookService ;

	/**
	 * @Title: 跳转到新主页
	 * @description: 展示新的数据
	 * @author: 31618
	 * @date: 2021/4/21
	 * @param: model
	 * @return:
	 */


	
	
	
}
