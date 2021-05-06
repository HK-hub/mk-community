package com.hk.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hk.community.dto.VideoDTO;
import com.hk.community.dto.VideoPaginationDTO;
import com.hk.community.model.Video;
import com.hk.community.model.VideoResource;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
* @mbg.generated
* generator on Mon Apr 19 15:01:57 GMT+08:00 2021
*/
public interface VideoService extends IService<Video> {


	String insertVideo(HttpServletRequest request, Map fileInfo);

	VideoPaginationDTO getVideoPaginationDTO(int stat);

	List<VideoResource> videoUrls(Video video);

	VideoDTO getVideoContent(Integer videoId) ;

}
