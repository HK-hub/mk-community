package com.hk.community.service.serviceImp;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hk.community.dto.VideoDTO;
import com.hk.community.dto.VideoPaginationDTO;
import com.hk.community.mapper.UserMapper;
import com.hk.community.mapper.VideoMapper;
import com.hk.community.model.User;
import com.hk.community.model.Video;
import com.hk.community.model.VideoType;
import com.hk.community.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
* @mbg.generated
* generator on Mon Apr 19 15:01:58 GMT+08:00 2021
*/
@Service
public class VideoServiceImpl  extends ServiceImpl<VideoMapper, Video> implements VideoService {

	@Autowired
	VideoMapper videoMapper ;
	@Autowired
	UserMapper userMapper ;
	@Autowired
	VideoResourceServiceImpl videoResourceService ;
	@Autowired
	VideoTypeServiceImpl videoTypeService ;

	@Override
	public String insertVideo(HttpServletRequest request, Map fileInfo) {

		final String videoName = request.getParameter("videoName");
		final String description = request.getParameter("description");
		final String target = request.getParameter("target");
		String copyright = request.getParameter("copyright");
		if (copyright == null){
			copyright = "1" ;
		}
		final String keywords = request.getParameter("keywords");
		//视频url 链接： 最后一个为封面
		final List<String> urls = (ArrayList)fileInfo.get("urls");
		//视频封面
		final String cover = urls.get(urls.size() - 1);
		final User user = (User)request.getSession().getAttribute("user");


		Video video = new Video();
		//设置video

		video.setCopyright(Integer.parseInt(copyright));
		video.setVideoName(videoName);
		video.setTarget(target);
		video.setKeywords(keywords);
		video.setCreator_id(user.getId());
		//1： 发布， 0. 审核
		video.setVideoStatus(1);
		video.setVideoCover(cover);
		video.setPublishedTime(new Date());
		video.setModifiedTime(video.getPublishedTime());

		String msg = "success";
		try {
			//插入video
			System.out.println("插入video");
			final int insert = videoMapper.insert(video);
			//插入videoResource 的链接
			System.out.println("插入videoResource 的链接");
			videoResourceService.insertVideoResource(video,urls);
			//插入videoType
			System.out.println("插入videoType");
			videoTypeService.insertVideoType(video);
			msg = "success" ;
		}catch (MybatisPlusException e) {
			e.printStackTrace();
			msg = "failed" ;
		}finally {
			return msg ;
		}
	}

	@Override
	public VideoPaginationDTO getVideoPaginationDTO(int state) {

		// 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题，这时候你需要自己查询 count 部分
		// page.setOptimizeCountSql(false);
		// 当 total 为小于 0 或者设置 setSearchCount(false) 分页插件不会进行 count 查询
		// 要点!! 分页返回的对象与传入的对象是同一个

		//第一个参数 current: 当前分页 ; 第二个参数size : 每页显示条数
		Page<Video> videoIPage = videoMapper.selectPage(new Page<Video>(state, 9), null);
		VideoPaginationDTO videoPaginationDTO = new VideoPaginationDTO(videoIPage);
		//对每一个questionIPage 中的元素进行处理
		List<Video> videoIPageRecords = videoIPage.getRecords();

		List<VideoDTO> videoDTOList = new LinkedList<>();
		for (Video record : videoIPageRecords) {
			VideoDTO videoDTO = new VideoDTO();
			User user = userMapper.findById(record.getCreator_id());
			final VideoType type = videoTypeService.getById(record.getTypeId());
			//获取资源地址
			final List resource = videoResourceService.getByVideoId(record.getId());
			BeanUtils.copyProperties(record, videoDTO);
			videoDTO.setCreator(user);
			videoDTO.setType(type);
			//设置资源地址
			videoDTO.setResourceUrls(resource);
			videoDTOList.add(videoDTO);
		}
		videoPaginationDTO.setVideoDTOS(videoDTOList);
		return videoPaginationDTO;
	}
}
