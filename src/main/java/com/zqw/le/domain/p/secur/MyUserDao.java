package com.zqw.le.domain.p.secur;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zqw.le.domain.p.jpa.CustomRepository;


public interface MyUserDao extends CustomRepository<MyUser, Long>{

}
