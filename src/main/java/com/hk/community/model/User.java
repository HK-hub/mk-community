package com.hk.community.model;

import lombok.Data;
import lombok.ToString;

/**
 * @author 31618
 * @description
 * @date 2021-03-27 18:16
 */

@Data
@ToString
public class User {

	private Integer id ;
	private String account_id ;
	private String name ;
	private String token ;
	private Long create_time ;
	private Long modified_time ;
	private String avatar_url ;  //头像地址

}
