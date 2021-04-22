package com.hk.community;

import com.hk.community.utils.DateUtil;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author 31618
 * @description
 * @date 2021-04-18 14:13
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FileTest {

	@Value("${qiniu.accessKey}")
	String accessKey ;
	@Value("${qiniu.secretKey}")
	String secretKey ;
	@Value("${qiniu.bucket}")
	String bucket;
	String key = "F:\\论坛社区\\community-2\\src\\main\\resources\\static\\css\\book-video\\img\\hero.jpg" ;

	@Test
	public void qiniuAPI(){

		Auth auth = Auth.create(accessKey,secretKey);
		//String upToken = auth.uploadToken(bucket);
		//System.out.println(upToken);

		StringMap putPolicy = new StringMap();
		putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
		long expireSeconds = 3600;
		//String upToken = auth.uploadToken(bucket, key, expireSeconds, putPolicy);

		putPolicy.put("callbackUrl", "http://api.example.com/qiniu/upload/callback");
		putPolicy.put("callbackBody", "key=$(key)&hash=$(etag)&bucket=$(bucket)&fsize=$(fsize)");
		String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
		System.out.println(upToken);

	}

	@Test
	public void dateUtil(){

		System.out.println(DateUtil.format(new Date(), "yyyy-MM-dd"));

	}

}
