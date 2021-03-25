package com.hk.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 31618
 * @description
 * @date 2021-03-23 17:57
 */
@Controller
public class IndexController {

	@RequestMapping("/")    //GetMapping
	public String index(){
		return "index" ;
	}

}
