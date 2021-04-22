package com.hk.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hk.community.dto.ArticleDTO;
import com.hk.community.dto.ArticlePaginationDTO;
import com.hk.community.model.Article;

/**
* @author 31618
 * @mbg.generated
* generator on Sat Apr 10 23:34:08 GMT+08:00 2021
*/
public interface ArticleService extends IService<Article> {

	//问题分页: IPage 对象中集成了 hasPreviourse() , hasNext() , getCountId() , getCurrent()
	public ArticlePaginationDTO selectArticlePage(Integer state);

	public ArticleDTO getArticleDTO(Integer id) ;

}
