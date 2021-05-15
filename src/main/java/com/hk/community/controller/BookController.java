package com.hk.community.controller;

import com.hk.community.dto.BookPaginationDTO;
import com.hk.community.service.serviceImp.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 31618
 * @description
 * @date 2021-04-29 0:33
 */
@Controller
public class BookController {

	@Autowired
	private BookServiceImpl bookService ;

	//视频书籍页面： 默认书籍
	@RequestMapping("/book")
	public String book(@RequestParam(name = "stat" ,defaultValue = "1" ,required = false)Integer stat,
	                   Model model){
		if (stat==null){
			stat = 1 ;
		}
		BookPaginationDTO bookPaginationDTO = bookService.getBookPaginationDTO(stat);
		model.addAttribute("bookPagination",bookPaginationDTO) ;
		return "book" ;
	}







}
