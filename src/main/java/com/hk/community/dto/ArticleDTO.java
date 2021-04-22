package com.hk.community.dto;

import com.hk.community.model.Category;
import com.hk.community.model.User;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author 31618
 * @description
 * @date 2021-04-12 9:43
 */
@Data
@ToString
public class ArticleDTO {

	private Integer id;

	private String title;

	private String content;

	private User creator;

	private Category category;

	private Integer viewCount;

	private Integer likeCount;

	private Integer collectCount;

	private Date createTime;




}
