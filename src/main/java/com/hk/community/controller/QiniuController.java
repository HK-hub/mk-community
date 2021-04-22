package com.hk.community.controller;

import com.hk.community.service.serviceImp.BookServiceImpl;
import com.hk.community.service.serviceImp.QiniuFileServiceImp;
import com.hk.community.service.serviceImp.VideoServiceImpl;
import com.hk.community.utils.QiniuUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * @author 31618
 * @description : 七牛云对象存储
 * @date 2021-04-18 15:37
 */
@Slf4j
@Controller
@RequestMapping("/qiniu")
public class QiniuController {

	@Autowired
	QiniuUtil qiniuUtil ;
	@Autowired
	QiniuFileServiceImp qiniuFileServiceImp ;
	@Autowired
	BookServiceImpl bookService ;
	@Autowired
	VideoServiceImpl videoService ;

	@RequestMapping("/file")
	public String file(){

		return "upload" ;
	}

	/**
	 * @Title:
	 * @description: multipartFile 最后一个为前端最后一个<input type="file' >标签上传的文件
	 * @author: 31618
	 * @date: 2021/4/20
	 * @param:
	 * @return:
	 */
	@PostMapping("/upload/{action}")    //代表存储类型
	@ResponseBody
	public String upload(@RequestParam("files") MultipartFile[] multipartFiles,
	                     Model model ,
	                     HttpServletRequest request,
	                     @PathVariable(name = "action" , value = "video", required = false)String action) throws IOException {

		//将文件存储到对象存储
		Map fileInfo = qiniuFileServiceImp.fileUplod(multipartFiles, model);
		String msg ;
		//String action = "book" ;
		//构造数据库存储对象
		if("video".equals(action)){
			//构造视频对象
			System.out.println("视频");
			msg = videoService.insertVideo(request, fileInfo);
		}
		else{
			//构造书籍对象
			System.out.println("书籍");
			msg = bookService.insertBook(request, fileInfo) ;
		}
		//将视频或者书籍url 放入model
		return "msg" ;

	}


}
