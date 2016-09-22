package com.zqw.le.domain.p;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import org.springframework.ui.Model;

@Entity
@Table(name="testTable1")
@NamedQueries(value={
		@NamedQuery(name="TestTable1.searchName",query="from TestTable1 a where a.name=?1")
})
@NamedNativeQueries(value={
		@NamedNativeQuery(name="TestTable1.testNativeQueryName",query="select a.id,a.name from test_table1 a where id =1 ",resultSetMapping="testResultMap"),
		@NamedNativeQuery(name="TestTable1.testNativeQuery",query="select a.id,a.name from test_table1 a where id =1 ",resultSetMapping="testResultMap1")
})
@SqlResultSetMappings(value={
		@SqlResultSetMapping(
				name="testResultMap",
				entities={},
				columns={
					@ColumnResult(name="name"),
					@ColumnResult(name="id")
				}
		),
		@SqlResultSetMapping(
				name="testResultMap1",
				entities={
						 @EntityResult(
								 entityClass = TestTable1.class,
								 fields={
										@FieldResult(name="id",column="id") ,
										@FieldResult(name="name",column="name")
								 }
								 
								 )
						 
				},
				columns={}
		)
})

public class TestTable1  {
	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String name;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TestTable1(String name) {
		super();
		this.name = name;
	}
	public TestTable1() {
		super();
	}
	@Override
	public String toString() {
		return "TestTable1 [id=" + id + ", name=" + name + "]";
	}
	
	
}
