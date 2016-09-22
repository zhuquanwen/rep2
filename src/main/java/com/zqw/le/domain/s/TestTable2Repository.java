package com.zqw.le.domain.s;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TestTable2Repository extends JpaRepository<TestTable2, Long>{
	public TestTable2 findByNameAndAge(String name,Integer age);
	@Query("from TestTable2 where name=:name")
	public TestTable2 findTable2(@Param("name") String name);
	public TestTable2 findByNameLike(String name);
	
	public TestTable2 findByNameIn(String[] names);
	
}
