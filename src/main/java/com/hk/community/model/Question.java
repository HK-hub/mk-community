package com.hk.community.model;

import lombok.Data;
import lombok.ToString;

/**
 * @author 31618
 * @description
 * @date 2021-03-29 15:45
 */
@Data
@ToString
public class Question {

	private Integer id ;
	private String title ;
	private String description ;
	private Long create_time ;
	private Long modified_time ;
	private Integer creator ;
	private Integer comment_count  = 0;
	private Integer view_count = 0;
	private Integer like_count = 0;
	private String tags = "测试标签";

}
