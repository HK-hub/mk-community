package com.hk.community.controller;

import com.hk.community.dto.QuestionDTO;
import com.hk.community.mapper.UserMapper;
import com.hk.community.model.User;
import com.hk.community.service.serviceImp.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 31618
 * @description
 * @date 2021-03-23 17:57
 */
@Controller
public class IndexController {

	@Autowired  //注入 UserMapper .来进行访问User数据库
	private UserMapper userMapper ;

//	@Autowired
//	private QuestionMapper questionMapper ;

	@Autowired
	private QuestionService questionService ;


	@RequestMapping("/")    //GetMapping
	public String index(HttpServletRequest request ,
	                    Model model){

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


		if (token != null){
			final User user = userMapper.findByToken(token);
			if (user != null){
				request.getSession().setAttribute("user",user);
			}
		}


		//查询所有文章
		List<QuestionDTO> questionList = questionService.allQuestionList();
		model.addAttribute("allQuestionList" , questionList) ;

		return "index" ;
	}

}
