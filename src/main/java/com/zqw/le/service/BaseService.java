package com.zqw.le.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.zqw.le.domain.p.TestTable1;
import com.zqw.le.domain.p.jpa.CustomRepository;


public interface BaseService<R extends Object,T extends CustomRepository> {
	public List<R> findAll();
	public Page<R> findAll(Pageable p);
	
	public Object save(R r);
	
	public R findOne(Serializable s);
	
	public void delete(R r);
	
	public List<T> findByAuto(T t);
	//。。。。。。
}
