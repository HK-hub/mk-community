package com.hk.community.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hk.community.model.Question;
import com.hk.community.model.User;
import com.hk.community.service.StatusService;
import com.hk.community.service.serviceImp.ProfileServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 31618
 * @description
 * @date 2021-04-05 16:08
 */
@Controller
public class ProfileController {

	@Autowired
	ProfileServiceImp profileServiceImp;


	public static String section = "questions" ;
	public static String sectionName = "我的文章" ;
	//请求路径动态参数： {pathvalue} ， 注解 @PathVariable 来解析请球路径参数
	@GetMapping(value = "/profile/{action}")
	public String profile( @PathVariable String action,
	                       @RequestParam(name = "index" ,defaultValue = "1") int index ,
	                       HttpServletRequest request ,
	                       Model model){
		//检查是否登录
		int loginStatus = StatusService.userLoginStatus(request);
		if (loginStatus == 0) {  //未登录
			return "index" ;
		}
		final User user = (User)request.getSession().getAttribute("user");
		switch (action){
			case "repies":
			case "messages":
				section = "repies" ;
				sectionName = "消息通知" ;
				break;
			case "resrc-tools":
				section = "resrc-tools" ;
				sectionName = "资源|工具" ;
				break;
			case "dynamic":
				section = "dynamic" ;
				sectionName = "最新动态" ;
				break;
			case "drafts":
				section = "drafts" ;
				sectionName = "我的草稿" ;
				break;
			case "collections":
				section = "collections" ;
				sectionName = "我的收藏" ;
				break;
			case "subscriptions":
				section = "subscriptions" ;
				sectionName = "我的关注" ;
				break;
			default:
				IPage<Question> userAllQuestions = profileServiceImp.getUserAllQuestions(user.getId(), index);
				model.addAttribute("userQuestions", userAllQuestions);
				section = "questions" ;
				sectionName = "我的文章" ;
				break;
		}
		model.addAttribute("section",section);
		model.addAttribute("sectionName", sectionName) ;
		return "profile" ;
	}


}
