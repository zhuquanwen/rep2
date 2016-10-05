package com.zqw.le.domain.p.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class CustomRepositoryImpl<T,ID extends Serializable> extends SimpleJpaRepository<T, ID> 
						implements CustomRepository<T, ID>{
	private final EntityManager entityManagerPrimary;
	public CustomRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.entityManagerPrimary = em;
	}

	@Override
	public List getMapResult(String sql) {
		Query query = entityManagerPrimary.createNativeQuery(sql);  
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);  
		List rows = query.getResultList();  
		return rows;
	}

	@Override
	public List<T> findByAuto(T e) {
		
		return findAll(CustomerSpecs.findByAuto(entityManagerPrimary,e));
	}
	

}
