package com.hk.community;

import com.hk.community.dto.VideoDTO;
import com.hk.community.dto.VideoPaginationDTO;
import com.hk.community.service.serviceImp.VideoServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author 31618
 * @description
 * @date 2021-04-29 21:08
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class VideoTest {

	@Autowired
	private VideoServiceImpl videoService ;

	@Test
	public void videoPage(){

		VideoPaginationDTO videoPaginationDTO =  videoService.getVideoPaginationDTO(1);
		final List<VideoDTO> videoDTOS = videoPaginationDTO.getVideoDTOS();
		for (VideoDTO dto : videoDTOS) {
			System.out.println(dto.getVideoName());
		}
	}



}
