package com.hk.community.controller;

import com.hk.community.dto.VideoDTO;
import com.hk.community.dto.VideoPaginationDTO;
import com.hk.community.service.serviceImp.UserServiceImp;
import com.hk.community.service.serviceImp.VideoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 31618
 * @description
 * @date 2021-04-29 0:35
 */
@Controller
public class VideoController {

	@Autowired
	private VideoServiceImpl videoService ;
	@Autowired
	private UserServiceImp userServiceImp ;


	//视频书籍页面： 默认书籍
	@RequestMapping("/video")
	public String video(@RequestParam(name = "stat" ,defaultValue = "1" ,required = false)Integer stat,
	                    Model model){
		if (stat==null){
			stat = 1 ;
		}
		VideoPaginationDTO videoPaginationDTO =  videoService.getVideoPaginationDTO(stat);
		model.addAttribute("videoPagination",videoPaginationDTO) ;
		return "video" ;

	}


	//查找单个视频
	@RequestMapping("/video-content")
	public String videoDetail(@RequestParam(name = "stat", defaultValue = "1")Integer count,
	                          Model model){

		//获取视频详细页面
		final VideoDTO videoContent = videoService.getVideoContent(count);
		//绑定数据
		model.addAttribute("singleVideoContent",videoContent) ;
		return "videoContent" ;
	}


}
