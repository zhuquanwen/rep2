package com.zqw.le.domain.p.oneMany;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="team")
public class Team {
	@Id
	@GenericGenerator(name="hibernate-uuid", strategy="uuid")
	@GeneratedValue(generator="hibernate-uuid")
	@Column(length=32)
	private String id;
	@Column(length=100)
	private String area;
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="stu_id",referencedColumnName="id",unique=true)
	private Student student;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
}
