package com.zqw.le.service.seviceImpl;

import org.springframework.stereotype.Service;

import com.zqw.le.domain.p.secur.MyUser;
import com.zqw.le.domain.p.secur.MyUserDao;
import com.zqw.le.service.MyUserService;

@Service
public class MyUserServiceImpl extends BaseServiceImpl<MyUser, MyUserDao> implements MyUserService{

}
