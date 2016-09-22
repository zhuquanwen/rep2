package com.zqw.le.service.seviceImpl;

import org.springframework.stereotype.Service;

import com.zqw.le.domain.p.oneMany.Author;
import com.zqw.le.domain.p.oneMany.AuthorDao;
import com.zqw.le.service.AuthorService;
@Service
public class AuthorServiceImpl extends BaseServiceImpl<Author, AuthorDao> implements AuthorService{

}
