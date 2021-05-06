package com.hk.community.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hk.community.model.Question;
import com.hk.community.model.User;
import org.springframework.ui.Model;

/**
 * @author 31618
 * @description
 * @date 2021-04-09 17:33
 */
public interface ProfileService {

	//查询当前用户的所有文章
	public IPage<Question> getUserAllQuestions(int userId, int index) ;


	void getPersonalProfile(User user, Model model);
}
