package com.hk.community.service.serviceImp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hk.community.model.Question;
import com.hk.community.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 31618
 * @description
 * @date 2021-04-09 18:57
 */
@Service
public class ProfileServiceImp implements ProfileService {
	@Autowired
	QuestionServiceImp questionServiceImp ;
	@Autowired
	UserServiceImp userServiceImp ;

	@Override
	public IPage<Question> getUserAllQuestions(int userId, int index) {
		//获取当前登录用户
		//User user = (User) request.getSession().getAttribute("user");
		//List<Question> questionList = questionServiceImp.list(queryWrapper);
		//自定义查询条件：
		QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("creator", userId);
		queryWrapper.orderByDesc("view_count","like_count","comment_count");
		Page<Question> questionPage = questionServiceImp.page(new Page<>(index, 10), queryWrapper);
		return questionPage;
	}
}