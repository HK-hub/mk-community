package com.hk.community.controller;

import com.alibaba.fastjson.JSON;
import com.hk.community.dto.ArticleDTO;
import com.hk.community.service.serviceImp.ArticleServiceImpl;
import com.hk.community.service.serviceImp.CategoryServiceImpl;
import com.hk.community.service.serviceImp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author 31618
 * @description : markdown 文章处理
 * @date 2021-04-10 23:56
 */
@Controller
public class ArticleController {

	@Autowired
	private ArticleServiceImpl articleService ;
	@Autowired
	private CategoryServiceImpl categoryService ;
	@Autowired
	private UserServiceImp userServiceImp ;


	//创作article
	@RequestMapping("/createArticle")
	public String createArticle(){

		return "edit" ;

	}

	//保存文章： 接受前端的markdown 数据
	/**
	 * @Title:
	 * @description:
	 * @author: 31618
	 * @date: 2021/4/11
	 * @param :
	 * @return: String
	 */
	@PostMapping("user/addArticle")
	@ResponseBody
	public String saveArticle(HttpSession session ,
	                          @RequestParam Map<String, String> paramete){
		boolean res = articleService.addArticle(session, paramete);
		if (res){
			paramete.put("status", "save success") ;
		}
		else {
			paramete.put("status", "save failed") ;
		}
		return JSON.toJSONString(paramete) ;
	}

	/**
	 * @Title: 保存文章草稿
	 * @description:
	 * @author: 31618
	 * @date: 2021/4/11
	 * @param :
	 * @return:
	 */
	@PostMapping("user/addDraft")
	@ResponseBody
	public String saveDraft(HttpSession session ,
	                        @RequestParam Map<String, String> paramete){
		boolean res = articleService.addArticle(session, paramete);
		if (res){
			paramete.put("status", "save success") ;
		}
		else {
			paramete.put("status", "save failed") ;
		}
		return JSON.toJSONString(paramete) ;
	}

	@RequestMapping("/showArticle")
	public String showAllArticles(@RequestParam(name = "count") Integer count ,
			Model model){

		final ArticleDTO articleDTO = articleService.getArticleDTO(count);
		model.addAttribute("article", articleDTO) ;
		return "article" ;
	}

}
