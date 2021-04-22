package com.hk.community.service.serviceImp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hk.community.mapper.VideoTypeMapper;
import com.hk.community.model.Video;
import com.hk.community.model.VideoType;
import com.hk.community.service.VideoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
* @mbg.generated
* generator on Mon Apr 19 19:17:40 GMT+08:00 2021
*/
@Service
public class VideoTypeServiceImpl extends ServiceImpl<VideoTypeMapper, VideoType> implements VideoTypeService {

	@Autowired
	VideoTypeMapper videoTypeMapper ;

	@Override
	public void insertVideoType(Video video) {

		VideoType videoType = new VideoType();
		videoType.setTypeName(video.getTarget());
		videoType.setCreatorId(video.getCreator_id());
		videoType.setVideoId(video.getId());
		videoType.setCreateTime(new Date());
		videoType.setModifiedTime(videoType.getCreateTime());
		//插入
		videoTypeMapper.insert(videoType);

	}
}
