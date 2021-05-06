package com.hk.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hk.community.dto.QuestionDTO;
import com.hk.community.model.Question;

/**
 * @author 31618
 * @description
 * @date 2021-04-02 8:26
 */

public interface QuestionService extends IService<Question> {


//	List<QuestionDTO> selectUserPage(int state);

	QuestionDTO getQuestion(int count) ;
}
