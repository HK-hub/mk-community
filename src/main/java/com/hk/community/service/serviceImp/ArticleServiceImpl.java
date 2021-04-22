package com.hk.community.service.serviceImp;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hk.community.dto.ArticleDTO;
import com.hk.community.dto.ArticlePaginationDTO;
import com.hk.community.mapper.ArticleMapper;
import com.hk.community.mapper.CategoryMapper;
import com.hk.community.mapper.UserMapper;
import com.hk.community.model.Article;
import com.hk.community.model.Category;
import com.hk.community.model.User;
import com.hk.community.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**]
* @mbg.generated
* generator on Sat Apr 10 23:34:09 GMT+08:00 2021
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper , Article> implements ArticleService {

	@Autowired
	private ArticleMapper articleMapper ;
	@Autowired
	private UserMapper userMapper ;
	@Autowired
	private CategoryMapper categoryMapper ;
	@Autowired
	private CategoryServiceImpl categoryService ;
	@Autowired
	private UserServiceImp userServiceImp ;
	/**
	 * @Title: 插入文章
	 * @description:
	 * @author: 31618
	 * @date: 2021/4/11
	 * @param : userid , article:
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


	@Override
	public ArticlePaginationDTO selectArticlePage(Integer state) {
		// 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题，这时候你需要自己查询 count 部分
		// page.setOptimizeCountSql(false);
		// 当 total 为小于 0 或者设置 setSearchCount(false) 分页插件不会进行 count 查询
		// 要点!! 分页返回的对象与传入的对象是同一个

		//第一个参数 current: 当前分页 ; 第二个参数size : 每页显示条数
		Page<Article> articleIPage = articleMapper.selectPage(new Page<Article>(state, 9), null);
		ArticlePaginationDTO articlePaginationDTO = new ArticlePaginationDTO(articleIPage);
		//对每一个questionIPage 中的元素进行处理
		List<Article> articleIPageRecords = articleIPage.getRecords();

		List<ArticleDTO> articleDTOList = new LinkedList<>();
		for (Article record : articleIPageRecords) {
			ArticleDTO dto = new ArticleDTO();
			User user = userMapper.findById(record.getCreatorId());
			Category category = categoryMapper.selectById(record.getCategoryId());
			BeanUtils.copyProperties(record, dto);
			dto.setCreator(user);
			dto.setCategory(category);
			articleDTOList.add(dto);
		}
		articlePaginationDTO.setArticleDTOS(articleDTOList);
		return articlePaginationDTO;

	}

	@Override
	public ArticleDTO getArticleDTO(Integer id) {
		Article article = articleMapper.selectById(id);
		final Category category = categoryService.getById(article.getCategoryId());
		final User user = userServiceImp.getById(article.getCategoryId());
		ArticleDTO articleDTO = new ArticleDTO();
		BeanUtils.copyProperties(article, articleDTO);
		articleDTO.setCreator(user);
		articleDTO.setCategory(category);
		return articleDTO;
	}


}
