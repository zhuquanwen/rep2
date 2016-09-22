package com.zqw.le.domain.p;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(value="transactionManagerPrimary",isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
public interface TestUuidRepository extends JpaRepository<TestUuid, Long>{
	
}
