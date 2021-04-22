package com.hk.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hk.community.model.Book;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
* @mbg.generated : 继承 IService 接口
* generator on Mon Apr 19 16:51:39 GMT+08:00 2021
*/
public interface BookService extends IService<Book>{


	String insertBook(HttpServletRequest request, Map fileInfo);
}
