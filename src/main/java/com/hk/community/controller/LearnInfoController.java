package com.hk.community.controller;

import com.hk.community.dto.VideoPaginationDTO;
import com.hk.community.service.serviceImp.VideoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 31618
 * @description : 书籍, 视频
 * @date 2021-04-19 18:50
 */
@Controller
public class LearnInfoController {
	
	@Autowired
	private VideoServiceImpl videoService ;


	/**
	 * @Title: 跳转到新主页
	 * @description: 展示新的数据
	 * @author: 31618
	 * @date: 2021/4/21
	 * @param: model
	 * @return:
	 */
	@RequestMapping("/learn")
	public String learn(){

		return "learn" ;
	}






	
	//视频书籍页面： 默认书籍
	@RequestMapping("/learnInfo/{action}")
	public String learnInfo(@PathVariable String action,
	                        @RequestParam(name = "stat" ,defaultValue = "1")Integer stat,
	                        Model model){

		if ("book".equals(action)){

			return "book" ;
		}else {

			VideoPaginationDTO videoPaginationDTO =  videoService.getVideoPaginationDTO(stat);
			model.addAttribute("videoPagination",videoPaginationDTO) ;
		}
		return "video" ;
	}
	




	
	
	
}
