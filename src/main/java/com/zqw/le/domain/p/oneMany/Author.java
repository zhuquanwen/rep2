package com.zqw.le.domain.p.oneMany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="author")
public class Author implements Serializable{
	@Id
	@GenericGenerator(name="hibernate-uuid", strategy="uuid")
	@GeneratedValue(generator="hibernate-uuid")
	@Column(length=32)
	private String id;
	
	//新增数据的时候必须通过mappedBy的字段为主新增方式，才会使得中间表数据增加，如果持久化此实体，不会对中间数据表新增数据
	@ManyToMany(cascade={CascadeType.MERGE,CascadeType.PERSIST},mappedBy="authors",fetch=FetchType.LAZY)
	private Set<Student> students=new HashSet<Student>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	
}
