package com.hk.community.dto;

import com.hk.community.model.User;
import lombok.Data;
import lombok.ToString;

/**
 * @author 31618
 * @description
 * @date 2021-03-30 21:47
 */
@Data
@ToString
public class QuestionDTO {
	private Integer id ;
	private String title ;
	private String description ;
	private Long create_time ;
	private Long modified_time ;
	private Integer creator ;
	private Integer comment_count ;
	private Integer view_count ;
	private Integer like_count;
	private String tags ;
	private User user ;

}
