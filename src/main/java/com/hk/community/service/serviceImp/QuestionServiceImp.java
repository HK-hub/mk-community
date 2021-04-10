package com.hk.community.service.serviceImp;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hk.community.dto.PaginationDTO;
import com.hk.community.dto.QuestionDTO;
import com.hk.community.mapper.QuestionMapper;
import com.hk.community.mapper.UserMapper;
import com.hk.community.model.Question;
import com.hk.community.model.User;
import com.hk.community.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class QuestionServiceImp extends ServiceImpl<QuestionMapper , Question> implements QuestionService {

	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private UserMapper userMapper ;

	/**
	 * @Title: 查询所有问题:
	 * @description: 需要将User 对象设置进入 questionDTO 中, user -> questionDTO
	 * @author: 31618
	 * @date: 2021/4/2
	 * @param :
	 * @return:
	 */
	public List<QuestionDTO> allQuestionList() {
		//查询到所有的Question
		List<Question> questions = questionMapper.allQuestionList();
		List<QuestionDTO> questionDTOList = new LinkedList<>();

		for (Question question : questions) {
			User user = userMapper.findById(question.getCreator());
			QuestionDTO questionDTO = new QuestionDTO();
			BeanUtils.copyProperties(question, questionDTO);
			questionDTO.setUser(user);
			questionDTOList.add(questionDTO);
		}
		return questionDTOList;
	}

	//问题分页: IPage 对象中集成了 hasPreviourse() , hasNext() , getCountId() , getCurrent()
	public PaginationDTO selectQuestionPage(Integer state) {
		// 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题，这时候你需要自己查询 count 部分
		// page.setOptimizeCountSql(false);
		// 当 total 为小于 0 或者设置 setSearchCount(false) 分页插件不会进行 count 查询
		// 要点!! 分页返回的对象与传入的对象是同一个

		//第一个参数 current: 当前分页 ; 第二个参数size : 每页显示条数
		Page<Question> questionIPage = questionMapper.selectPage(new Page<Question>(state, 10), null);
		PaginationDTO paginationDTO = new PaginationDTO(questionIPage);
		//对每一个questionIPage 中的元素进行处理
		List<Question> questionIPageRecords = questionIPage.getRecords();

		List<QuestionDTO> questionDTOList = new LinkedList<>();
		for (Question record : questionIPageRecords) {
			QuestionDTO dto = new QuestionDTO();
			User user = userMapper.findById(record.getCreator());
			BeanUtils.copyProperties(record, dto);
			dto.setUser(user);
			questionDTOList.add(dto);
		}
		paginationDTO.setQuestionDTOS(questionDTOList);
		return paginationDTO;
	}





}