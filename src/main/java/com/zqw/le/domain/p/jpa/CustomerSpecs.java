package com.zqw.le.domain.p.jpa;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import com.zqw.le.domain.p.TestTable1;

public class CustomerSpecs {
	public static Specification<TestTable1> test1TableSomething(){
		return new Specification<TestTable1>() {
			@Override
			public Predicate toPredicate(Root<TestTable1> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> pd = new ArrayList<Predicate>();
				pd.add(cb.equal(root.get("name"), "测试"));
				pd.add(cb.notEqual(root.get("id"), 112));
				Predicate[] pre = new Predicate[pd.size()];
				return query.where(pd.toArray(pre)).getRestriction();
			}
		};
	}
	
	public static <T> Specification<T> findByAuto(final EntityManager entityManager,final T t){
		final Class<T> type = (Class<T>) t.getClass();
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				EntityType<T> entity = entityManager.getMetamodel().entity(type);
				for (Attribute<T, ?> attr : entity.getDeclaredAttributes()) {
					Object attrValue = getValue(t,attr);
					if(attrValue != null){
						if(attr.getJavaType() == String.class){
							if(!StringUtils.isEmpty(attrValue)){
								Predicate pre = cb.like(root.get(attribute(entity, attr.getName(), String.class)), pattern((String)attrValue));
								predicates.add(pre);
							}
						} else{
							predicates.add(cb.equal(root.get(attribute(entity,attr.getName(),attrValue.getClass())), attrValue));
						}
					}
				}
				Predicate[] pre = new Predicate[predicates.size()];
				return predicates.isEmpty() ? cb.conjunction() : cb.and(predicates.toArray(pre));//11
				/*return predicates.isEmpty() ? cb.conjunction() : null;*/
			}
			private <T> Object getValue(T t ,Attribute<T,?> attr){
				return ReflectionUtils.getField((Field)attr.getJavaMember(), t);
			}
			private <E, T> SingularAttribute<T, E> attribute(EntityType<T> entity, String fieldName,
					Class<E> fieldClass) { 
				return entity.getDeclaredSingularAttribute(fieldName, fieldClass);
			}
		};
	}
	static private String pattern(String str){
		return "%"+str+"%";
	}
}
