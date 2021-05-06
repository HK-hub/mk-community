package com.hk.community.service.serviceImp;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hk.community.dto.BookDTO;
import com.hk.community.dto.BookPaginationDTO;
import com.hk.community.mapper.BookMapper;
import com.hk.community.mapper.UserMapper;
import com.hk.community.model.Book;
import com.hk.community.model.User;
import com.hk.community.service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @mbg.generated : 继承 ServiceImpl
* generator on Mon Apr 19 16:51:39 GMT+08:00 2021
*/
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

	@Autowired
	BookMapper bookMapper ;
	@Autowired
	UserMapper userMapper ;
	@Override
	public String insertBook(HttpServletRequest request, Map fileInfo) {
		//书籍名称
		final String bookName = request.getParameter("bookName");
		//书籍作者
		final String author = request.getParameter("author");
		final String target = request.getParameter("target");
		final String description = request.getParameter("description");
		//上传者
		final User user = (User) request.getSession().getAttribute("user");
		//资源地址
		final List<String> urls = (ArrayList)fileInfo.get("urls");
		Book book = new Book();
		book.setAuthor(author);
		book.setCategory(target);
		book.setCreate_time(new Date());
		book.setModified_time(book.getCreate_time());
		book.setName(bookName);
		book.setSharer(user.getId());
		//设置书籍资源地址， 书籍封面地址
		book.setSource(urls.get(0));
		System.out.println(book.getSource());
		book.setBook_cover(urls.get(urls.size() - 1));
		System.out.println(book.getBook_cover());
		//插入书籍
		try {
			bookMapper.insert(book) ;
			return "success" ;
		}catch (MybatisPlusException exception){
			exception.printStackTrace();
			return "failed" ;
		}
	}

	@Override
	public BookPaginationDTO getBookPaginationDTO(int stat) {
		Page<Book> bookPage = bookMapper.selectPage(new Page<Book>(stat, 9), null);
		BookPaginationDTO bookPaginationDTO = new BookPaginationDTO(bookPage);
		List<Book> records = bookPage.getRecords();
		List<BookDTO> dtoList = new ArrayList<>();
		for (Book book : records){
			BookDTO bookDTO = new BookDTO();
			final User user = userMapper.selectById(book.getSharer());
			BeanUtils.copyProperties(records, bookDTO);
			bookDTO.setSharer(user);
			dtoList.add(bookDTO);
		}
		bookPaginationDTO.setBookDTOS(dtoList);
		return bookPaginationDTO;
	}


}
