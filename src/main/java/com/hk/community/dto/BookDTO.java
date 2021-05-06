package com.hk.community.dto;

import com.hk.community.model.User;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author 31618
 * @description
 * @date 2021-04-29 0:50
 */
@Data
@ToString
public class BookDTO {
	private Integer id ;
	private String name ;
	private String author = "小码客" ;
	private String source ;     //书籍url 地址
	private String book_cover ;     //书籍封面
	private String category ;   //分类
	private User sharer ;        //分享者
	private int score ;         //评分
	private String language ;
	private int chapters ;
	private Integer view_count;
	private Integer collect_count;
	private Date create_time;
	private Date modified_time;
}
