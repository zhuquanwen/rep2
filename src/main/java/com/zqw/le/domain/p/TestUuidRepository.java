package com.zqw.le.domain.p;


import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zqw.le.domain.p.jpa.CustomRepository;

@Transactional(value="transactionManagerPrimary",isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
public interface TestUuidRepository extends CustomRepository<TestUuid, Long>{
	
}
