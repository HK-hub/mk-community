package com.hk.community.service.serviceImp;

import com.hk.community.dto.QuestionDTO;
import com.hk.community.mapper.QuestionMapper;
import com.hk.community.mapper.UserMapper;
import com.hk.community.model.Question;
import com.hk.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 31618
 * @description
 * @date 2021-03-30 21:50
 */

@Service
public class QuestionService {

	@Autowired
	QuestionMapper questionMapper ;
	@Autowired
	UserMapper userMapper ;


	public List allQuestionList() {
		//查询到所有的Question
		List<Question> questions = questionMapper.allQuestionList();
		List<QuestionDTO> questionDTOList = new LinkedList<>();

		for (Question question : questions) {
			User user = userMapper.findById(question.getCreator()) ;
			System.out.println("user 为空" + user.toString());

			System.out.println(question.getTitle());
			System.out.println(question.getDescription());
			System.out.println(question.getTags());
			System.out.println(question.getView_count());
			QuestionDTO questionDTO = new QuestionDTO();
			BeanUtils.copyProperties(question, questionDTO);
			System.out.println(questionDTO.getTitle());
			System.out.println(questionDTO.getDescription());
			System.out.println(questionDTO.getTags());
			questionDTO.setUser(user);
			questionDTOList.add(questionDTO);

			System.out.println("-----------头像-------------");
			System.out.println(questionDTO.getUser().getAvatar_url());
		}

		return questionDTOList ;
	}
}
