package com.zqw.le.domain.p.moreTS;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ADao extends JpaRepository<A, Long>{
	
	/**通过构造函数来进行多表查询*/
	@Query("select new com.zqw.le.domain.p.moreTS.A(a.c1,a.c2,b.c3,b.c4) from A a,B b where a.c1 = b.c3")
	public List<A> findAs();
}
