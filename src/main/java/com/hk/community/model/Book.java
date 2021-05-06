package com.hk.community.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author 31618
 * @description ： 书籍类型
 * @date 2021-04-14 11:52
 */
@Data
@ToString
@TableName("tb_book")
public class Book {

	@TableId(value = "id" , type = IdType.AUTO)
	private Integer id ;
	private String name ;
	private String author = "小码客" ;
	private String source ;     //书籍url 地址
	private String book_cover ;     //书籍封面
	private String category ;   //分类
	private int sharer ;        //分享者
	private int score ;         //评分
	private String language ;
	private int chapters ;
	private Integer view_count;
	private Integer collect_count;
	private Date create_time;
	private Date modified_time;

}
