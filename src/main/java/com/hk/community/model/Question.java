package com.hk.community.model;

import lombok.Data;

/**
 * @author 31618
 * @description
 * @date 2021-03-29 15:45
 */
@Data
public class Question {

	private Integer id ;
	private String title ;
	private String description ;
	private Long create_time ;
	private Long modified_time ;
	private Integer creator ;
	private Integer comment_count ;
	private Integer view_count ;
	private Integer like_count ;
	private String tags ;

	@Override
	public String toString() {
		return "Question{" +
				"id=" + id +
				", title='" + title + '\'' +
				", description='" + description + '\'' +
				", create_time=" + create_time +
				", modified_time=" + modified_time +
				", creator=" + creator +
				", comment_count=" + comment_count +
				", view_count=" + view_count +
				", like_count=" + like_count +
				", tags='" + tags + '\'' +
				'}';
	}
}
