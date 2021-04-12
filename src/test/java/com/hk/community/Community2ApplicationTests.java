package com.hk.community;

import com.hk.community.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class Community2ApplicationTests {

	@Autowired
	QuestionService questionService;
//	@Autowired
//	QuestionMapper questionMapper;

	@Test
	void contextLoads() {

//		Page<Question> questionPage = questionService.page(new Page<Question>());
//		System.out.println(questionPage.getTotal());
		System.out.println(System.currentTimeMillis());
	}

}
