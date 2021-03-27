package com.hk.community.controller;

import com.hk.community.dto.AccessTokenDTO;
import com.hk.community.dto.GithubUser;
import com.hk.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 31618
 * @description
 * @date 2021-03-26 19:42
 */
@Controller
public class AuthorizeController {

	@Autowired
	private GithubProvider githubProvider ;
	//属性注入
	@Value("${github.client.id}")
	private String clientId ;
	@Value("${github.client.secret}")
	private String clientSecret ;
	@Value("${github.redirect.uri}")
	private String redirectURL ;


	@GetMapping("/callback")
	public String callback(@RequestParam(name = "code")String code,
	                       @RequestParam(name = "state")String state){
		AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
		//: 重定向回站点, 使用 code 换令牌
		accessTokenDTO.setCode(code);
		accessTokenDTO.setRedirect_url(redirectURL);
		accessTokenDTO.setState(state);
		accessTokenDTO.setClient_id(clientId);
		accessTokenDTO.setClient_secret(clientSecret);
		String token = githubProvider.getAccessToken(accessTokenDTO);
		GithubUser githubUser = githubProvider.getUser(token);
		System.out.println(githubUser.getName());

		return "index" ;
	}

}
