package com.hk.community.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * @author 31618
 * @description
 * @date 2021-03-27 18:16
 */

@Data
@ToString
@TableName("tb_user")
public class User {

	@TableId(value = "id" , type = IdType.AUTO)     //设置主键自增
	private Integer id ;
	private String account_id ;
	private String password ;
	private String email ;
	private String name ;
	private String token ;
	private Long create_time ;
	private Long modified_time ;
	private Integer subscription = 10;
	//头像地址
	private String avatar_url ;
	private String bio = "a plain coder";




}
