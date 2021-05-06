package com.hk.community.service.serviceImp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hk.community.model.*;
import com.hk.community.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

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
	@Autowired
	ArticleServiceImpl articleService ;
	@Autowired
	VideoServiceImpl videoService ;
	@Autowired
	BookServiceImpl bookService ;



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

	@Override
	public void getPersonalProfile(User user, Model model) {

		final Integer userId = user.getId();
		//获取问题列表信息
		final List<Question> questionList = questionServiceImp.list(new QueryWrapper<Question>().eq("creator", userId));
		//获取文章列表
		final List<Article> articleList = articleService.list(new QueryWrapper<Article>().eq("creator_id", userId));
		//获取视频列表
		final List<Video> videoList = videoService.list(new QueryWrapper<Video>().eq("creator_id", userId));
		//获取书籍列表
		final List<Book> bookList = bookService.list(new QueryWrapper<Book>().eq("share", userId));


		//添加信息
		model.addAttribute("person", user);
		model.addAttribute("questionList",questionList);
		model.addAttribute("articleList",articleList);
		model.addAttribute("videoList",videoList);
		model.addAttribute("bookList", bookList);
	}
}