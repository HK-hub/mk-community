package com.hk.community.service.serviceImp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hk.community.mapper.VideoResourceMapper;
import com.hk.community.model.Video;
import com.hk.community.model.VideoResource;
import com.hk.community.service.VideoResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @mbg.generated
* generator on Tue Apr 20 12:51:11 GMT+08:00 2021
*/
@Service
public class VideoResourceServiceImpl extends ServiceImpl<VideoResourceMapper,VideoResource> implements VideoResourceService{

	@Autowired
	VideoResourceMapper videoResourceMapper ;


	@Override
	public void insertVideoResource(Video video, List<String> urls) {

		//插入urls
		for (int i = 0; i < urls.size() - 1; i++) {
			VideoResource videoResource = new VideoResource();
			videoResource.setVideoId(video.getId());
			videoResource.setVideoName(video.getVideoName());
			videoResource.setVideoResource(urls.get(i));
			//插入
			videoResourceMapper.insert(videoResource) ;
		}
	}

	@Override
	public List getByVideoId(Integer id) {
		QueryWrapper<VideoResource> videoResourceQueryWrapper = new QueryWrapper<>();
		videoResourceQueryWrapper.eq("video_id", id);
		List<VideoResource> resourceList = videoResourceMapper.selectList(videoResourceQueryWrapper);
		return resourceList ;
	}
}
