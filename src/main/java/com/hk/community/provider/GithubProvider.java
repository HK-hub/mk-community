package com.hk.community.provider;

import com.alibaba.fastjson.JSON;
import com.hk.community.dto.AccessTokenDTO;
import com.hk.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author 31618
 * @description
 * @date 2021-03-26 19:54
 */
@Component  //注册组件到容器
public class GithubProvider {

	private static String url = "https://github.com/login/oauth/access_token";

	//需要使用post  方法
	public String getAccessToken(AccessTokenDTO accessTokenDTO){

		OkHttpClient client = new OkHttpClient();
		MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
		RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
		Request request = new Request.Builder()
				.url(url)
				.post(body)
				.build();
		try {
			Response response = client.newCall(request).execute() ;
			//获取的是 access_token=xxxxx&scope=xxxx&token_type=bearer
			String string = response.body().string();
			//获取access_token: 通过 & 切割字符串
			String token = string.split("&")[0].split("=")[1];
			//打印一下String
			System.out.println(string);
			System.out.println(token);
			return token;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null ;
	}

	//使用Get 方法
	public GithubUser getUser(String access_token){
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("https://api.github.com/user?access_token=" + access_token)
				.build();

		try {
			Response response = client.newCall(request).execute();
			String string = response.body().string();
			GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
			return githubUser ;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null ;
	}


}
