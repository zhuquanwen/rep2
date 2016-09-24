package com.zqw.le.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

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
}
