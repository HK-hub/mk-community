package com.hk.community;

import com.hk.community.model.Article;
import com.hk.community.service.serviceImp.ArticleServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 31618
 * @description
 * @date 2021-04-12 10:12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ArticleTest {

	@Autowired
	ArticleServiceImpl articleService ;


	@Test
	public void contentTest(){

		final Article serviceById = articleService.getById(1);
		System.out.println(serviceById.getTitle());
		System.out.println(serviceById.getContent());

	}


}
