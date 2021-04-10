package com.hk.community;

import com.hk.community.dto.QuestionDTO;
import com.hk.community.mapper.QuestionMapper;
import com.hk.community.model.Question;
import com.hk.community.service.serviceImp.QuestionServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author 31618
 * @description
 * @date 2021-04-02 7:57
 */
@SpringBootTest
@RunWith(value = SpringRunner.class)
public class QuestionTest {

	@Autowired
	private QuestionServiceImp questionService ;

	@Autowired
	private QuestionMapper questionMapper ;





	@Test
	public void questionPageTest(){

		System.out.println("-------------------");

		List<QuestionDTO> questionList = questionService.allQuestionList();

		List<Question> questions = questionService.list();

		for (Question question : questions) {
			System.out.println(question.getCreate_time());
		}

		System.out.println("lllllllllllllllllllllllllllllllll");
		final List<Question> allQuestionList = questionMapper.allQuestionList();
		for (Question question : allQuestionList) {
			System.out.println(question.getCreate_time());
		}

		System.out.println("9999999999999999999999999999999999999999");
		for (QuestionDTO dto : questionList) {
			System.out.println(dto.getTitle());
			System.out.println(dto.getDescription());
			System.out.println(dto.getCreate_time());
			System.out.println(dto.getCreator());
			System.out.println("---------------------");

		}

	}

}
