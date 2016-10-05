package com.zqw.le.domain.p.jpa;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**自定义接口*/
@NoRepositoryBean
public interface CustomRepository<T,ID extends Serializable> extends JpaRepository<T, ID> ,JpaSpecificationExecutor<T> {
	public List getMapResult(String sql);
	public List<T> findByAuto(T e);
}
