package com.zqw.le.domain.p.secur;


import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.zqw.le.domain.p.jpa.CustomRepository;

@CacheConfig(cacheNames = "roles")
public interface RoleDao extends CustomRepository<Role, Long>{
	@Cacheable
	@Override
	List<Role> findAll();
	
	@CachePut
	@Override
	<S extends Role> S save(S arg0);
	
}
