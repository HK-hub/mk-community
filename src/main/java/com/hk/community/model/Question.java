package com.hk.community.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * @author 31618
 * @description
 * @date 2021-03-29 15:45
 */
@Data
@ToString
@TableName(value = "tb_question")
public class Question {

	@TableId(value = "id" , type = IdType.AUTO)
	private Integer id ;
	private String title ;
	private String description ;
	private Long create_time ;
	private Long modified_time ;
	private Integer creator ;
	private Integer comment_count  ;
	private Integer view_count ;
	private Integer like_count ;
	private String tags = "测试标签";

}
