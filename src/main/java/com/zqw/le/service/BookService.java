package com.zqw.le.service;

import org.springframework.data.repository.query.Param;

import com.zqw.le.domain.p.oneMany.Book;
import com.zqw.le.domain.p.oneMany.BookDao;

public interface BookService extends BaseService<Book,BookDao>{
	public Book findBookByStudent(String num);
}
