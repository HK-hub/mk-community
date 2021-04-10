package com.hk.community.controller;

import com.hk.community.dto.PaginationDTO;
import com.hk.community.model.User;
import com.hk.community.service.serviceImp.QuestionServiceImp;
import com.hk.community.service.serviceImp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 31618
 * @description
 * @date 2021-03-23 17:57
 */
@Controller
public class IndexController {

	@Autowired
	private QuestionServiceImp questionService ;
	@Autowired
	private UserServiceImp userService ;



	@RequestMapping("/")    //GetMapping
	public String index(HttpServletRequest request ,
	                    Model model ,
	                    @RequestParam(name = "pageIndex" ,defaultValue = "1") int pageIndex){

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
		//根据Token 获取 User对象
		if (token != null){
			User user = userService.findUserByToken(token);
			if (user != null){
				request.getSession().setAttribute("user",user);
			}
		}

		//查询所有文章
		//分页查询文章: pageIndex
		PaginationDTO selectQuestionPage = questionService.selectQuestionPage(pageIndex);
		model.addAttribute("questionPage" , selectQuestionPage) ;

		return "index" ;
	}

}
