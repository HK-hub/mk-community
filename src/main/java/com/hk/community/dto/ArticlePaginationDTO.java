package com.hk.community.dto;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hk.community.model.Article;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 31618
 * @description
 * @date 2021-04-03 15:15
 */
@Data
@ToString
public class ArticlePaginationDTO {

	private IPage<Article>  articleIPage ;
	private List<ArticleDTO> articleDTOS ;
	private boolean showPrevious ;
	private boolean showFirstPage ;
	private boolean showNext ;
	private boolean showEndPage ;
	private Long page ; //总页数
	private Long total ;    //总条数
	private Long current ; //当前页数
	private List<Long> pages = new ArrayList<>();

	public ArticlePaginationDTO(IPage<Article>  iPage){
		this.articleIPage = iPage ;
		this.page = iPage.getPages() ;
		this.total = iPage.getTotal() ;
		this.current = iPage.getCurrent();
		//页数设置
		initPage();
		//展示页面设置
		initViewPage();
	}
	public void initViewPage(){
		pages.add(current) ;

		for (int i = 1; i <= 3 ; i++) {
			if (current - i > 0){
				pages.add(0, current-i);
			}
			if (current + i <= page){
				pages.add(current + i) ;
			}
		}
	}

	public void initPage(){
		this.showPrevious = !(this.current == 1);
		this.showNext = !(this.current.equals(this.page)) ;
		//展示5个分页按钮： 当前页 - 4 > 0  显示第一页
		this.showFirstPage = current - 4 > 0 ? true : false ;
		// 最后一页 - 当前页 >  4 显示最后一页
		this.showEndPage = total - current > 4 ? true : false;
	}




}
