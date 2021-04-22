package com.hk.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 31618
 * @description
 * @date 2021-04-15 19:48
 */
@Controller
public class TestController {
	
	@RequestMapping("/test")
	public String test(){

		return "edit" ;
	}

}
