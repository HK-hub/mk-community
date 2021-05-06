package com.hk.community.controller;

import com.hk.community.mapper.QuestionMapper;
import com.hk.community.model.Question;
import com.hk.community.model.User;
import com.hk.community.service.serviceImp.QuestionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 31618
 * @description
 * @date 2021-03-28 16:41
 */

@Controller
public class PublishController {

	@Autowired
	private QuestionServiceImp questionServiceImp ;

	@Autowired
	private QuestionMapper questionMapper ;

	@RequestMapping("/publish")
	public String publish(){

		return "question" ;
	}

	@PostMapping("/publish/save_question")
	public String saveQuestion(@RequestParam("title") String title ,
	                           @RequestParam("description") String description ,
	                           @RequestParam(value = "tags", required = false) String[] tags,
	                           HttpServletRequest request,
	                           Model model
	){
		String tagList = "";
		for(String tag: tags){
			tagList+=tag+";" ;
		}
		Question question = new Question();
		question.setTags(tagList);
		question.setTitle(title);
		question.setDescription(description);
		question.setCreate_time(System.currentTimeMillis());
		question.setModified_time(question.getCreate_time());
		User user = (User) request.getSession().getAttribute("user");

		if (user == null){
			model.addAttribute("user_login_err", "错误: 用户未登录") ;
			return "signIn" ;
		}
		else{
			question.setCreator(user.getId());
			//插入数据
			//questionServiceImp.save(question) ;
			questionMapper.insertQuestion(question);
		}

		return "redirect:/" ;
	}

}
