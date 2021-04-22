package com.hk.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hk.community.model.Video;
import com.hk.community.model.VideoResource;

import java.util.List;

/**
* @mbg.generated
* generator on Tue Apr 20 12:51:11 GMT+08:00 2021
*/
public interface VideoResourceService extends IService<VideoResource> {


	void insertVideoResource(Video video, List<String> urls);

	List getByVideoId(Integer id);
}
