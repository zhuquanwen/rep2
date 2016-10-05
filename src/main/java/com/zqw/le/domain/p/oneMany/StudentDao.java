package com.zqw.le.domain.p.oneMany;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zqw.le.domain.p.jpa.CustomRepository;


public interface StudentDao extends CustomRepository<Student, Long>{
	
}
