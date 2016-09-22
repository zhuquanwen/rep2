package com.zqw.le.domain.p.oneMany;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookDao extends JpaRepository<Book, Long>{
	@Query("from Book t left join fetch t.student s where   s.num = ?1 ")
	public Book fBookByStudent( String num);
	
}
