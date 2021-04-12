package com.hk.community.service.serviceImp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hk.community.mapper.ArticleMapper;
import com.hk.community.model.Article;
import com.hk.community.model.User;
import com.hk.community.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
* @mbg.generated
* generator on Sat Apr 10 23:34:09 GMT+08:00 2021
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper , Article> implements ArticleService {

	@Autowired
	private ArticleMapper articleMapper ;


	/**
	 * @Title: 插入文章
	 * @description:
	 * @author: 31618
	 * @date: 2021/4/11
	 * @param  userid, article:
	 * @return: boolean
	 */
	public boolean addArticle(HttpSession session, Map<String, String> paramete) {

		try {
			final User user = (User)session.getAttribute("user");
			System.out.println(user.getEmail());
			Article article = new Article();
			final String title = paramete.get("title");
			final String content = paramete.get("content");
			final String status = paramete.get("status");
			System.out.println(content.length());
			System.out.println(title);
			//设置文章数据
			article.setCreatorId(user.getId());
			article.setTitle(title);
			article.setContent(content);
			article.setStatus(status);
			article.setCreateTime(new Date(System.currentTimeMillis()));
			article.setModifiedTime(article.getCreateTime());

			articleMapper.insert(article) ;
			return  true;
		}catch (Exception e){
			System.out.println("错误-----------------");
			e.printStackTrace();
			System.out.println(e.getClass());
			return false;
		}

	}





}
