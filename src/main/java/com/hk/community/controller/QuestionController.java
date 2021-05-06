package com.hk.community.controller;


import com.hk.community.dto.PaginationDTO;
import com.hk.community.dto.QuestionDTO;
import com.hk.community.service.serviceImp.QuestionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 31618
 * @description
 * @date 2021-04-30 2:02
 */
@Controller
public class QuestionController {

	@Autowired
	private QuestionServiceImp questionService ;

	@RequestMapping("/questionPage")
	public String questionPage(@RequestParam(name = "page", defaultValue = "1") int page ,
	                           Model model
	                           ){

		//查询所有文章
		//分页查询文章: pageIndex
		PaginationDTO selectQuestionPage = questionService.selectQuestionPage(page);
		model.addAttribute("questionPage" , selectQuestionPage) ;
		return "questionPage";

	}

	@RequestMapping("/questionContent")
	public String questionContent(@RequestParam(name = "count", defaultValue = "1") int count,
	                              Model model){
		System.out.println(count);
		final QuestionDTO question = questionService.getQuestion(count);
		model.addAttribute("questionDetail", question) ;

		return "singleQuestion" ;
	}




}
