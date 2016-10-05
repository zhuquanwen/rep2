package com.zqw.le.domain.p;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zqw.le.domain.p.jpa.CustomRepository;


@Transactional(value="transactionManagerPrimary",isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
@RepositoryRestResource(path = "tt")
public interface TestTable1Repository extends CustomRepository<TestTable1, Long>,JpaSpecificationExecutor<TestTable1> {
	public TestTable1 findByName(String name);
	@Query("from TestTable1 where name=:name")
	public TestTable1 findTestTable1(@Param("name") String name);

	@RestResource(path="findTT1",rel="findTT1")
	@Query("select a from TestTable1 a where a.name = ?1 ")
	public TestTable1 findTT1(String name);
	

	@Modifying
	@Query("update TestTable1 a set a.name=?1 where a.id=1")
	public void updateTestTable1Name(String name);
	
	public TestTable1 searchName(String name);
	
	public Object[] testNativeQueryName();
	
	public TestTable1 testNativeQuery();
	
	public Page<TestTable1> findByNameLike(String name,Pageable page);
	
	@Query(value="select * from test_table1 where name like %?1%",nativeQuery=true)
	public List<TestTable1> findByNameNative(String name);
	
	public List<TestTable1> findByAuto(TestTable1 t);
}
