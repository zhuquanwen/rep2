package com.zqw.le.domain.s;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="testTable2")
public class TestTable2 {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(nullable=true)
	private String name;
	@Column
	private Integer age;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "TestTable2 [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	public TestTable2(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	public TestTable2() {
		super();
	}
	
}
