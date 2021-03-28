package com.hk.community.utils;

import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author 31618
 * @description
 * @date 2021-03-28 14:15
 */
@Component
public class HttpHelper {
	//
	public String Get(String url) {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
				.url(url)
				.build();
		try {
			Response response = client.newCall(request).execute();
			return response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Post 请求
	public String Post(String url, String json) {
		OkHttpClient client = new OkHttpClient();
		MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
		RequestBody body = RequestBody.create(mediaType, json);
		Request request = new Request.Builder()
				.url(url)
				.post(body)
				.build();
		try {
			Response response = client.newCall(request).execute();
			return response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
