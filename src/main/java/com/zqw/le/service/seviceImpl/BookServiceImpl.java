package com.zqw.le.service.seviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zqw.le.domain.p.oneMany.Book;
import com.zqw.le.domain.p.oneMany.BookDao;
import com.zqw.le.service.BookService;
@Service
public class BookServiceImpl extends BaseServiceImpl<Book, BookDao> implements BookService{
	@Autowired
	private BookDao bookDao;
	@Override
	public Book findBookByStudent(String num) {
		return bookDao.fBookByStudent(num);
	}

}
