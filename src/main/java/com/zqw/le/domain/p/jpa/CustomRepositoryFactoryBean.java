package com.zqw.le.domain.p.jpa;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

public class CustomRepositoryFactoryBean<T extends JpaRepository<S, ID>,S,ID extends Serializable> 
						extends JpaRepositoryFactoryBean<T, S, ID>{
	@Override
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManagerPrimary) {
		/*return super.createRepositoryFactory(entityManager);*/
		return new CustomRepositoryFactory(entityManagerPrimary);
	}
	
	private static class CustomRepositoryFactory extends JpaRepositoryFactory{

		public CustomRepositoryFactory(EntityManager entityManagerPrimary) {
			super(entityManagerPrimary);
		}
		@Override
		protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(
				RepositoryInformation information, EntityManager entityManagerPrimary) {
			/*return super.getTargetRepository(information, entityManager);*/
			return new CustomRepositoryImpl<T, ID>((Class<T>)information.getDomainType(), entityManagerPrimary);
		}
		@Override
		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			/*return super.getRepositoryBaseClass(metadata);*/
			return CustomRepositoryImpl.class;
		}
	}
}
