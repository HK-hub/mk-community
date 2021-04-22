package com.hk.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hk.community.model.Video;
import com.hk.community.model.VideoType;

/**
* @mbg.generated
* generator on Mon Apr 19 19:17:40 GMT+08:00 2021
*/
public interface VideoTypeService extends IService<VideoType> {


	void insertVideoType(Video video);
}
