package com.zqw.le.domain.p.oneMany;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeamDao extends JpaRepository<Team, Long>{
	@Query("from Team t where t.student.name=?1")
	public List<Team> findByStudentName(String name);
}
