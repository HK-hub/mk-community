package com.hk.community.dto;

import lombok.Data;

/**
 * @author 31618
 * @description
 * @date 2021-03-26 19:57
 */
@Data
public class AccessTokenDTO {
	private String client_id ;
	private String client_secret = "d159b27db3d05e216f00259034bc4c14d51107e9";
	private String code ;
	private String redirect_url ;
	private String state ;



}
