package com.zqw.le.domain.p.moreTS;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="b")
public class B {
	@Id
	@GenericGenerator(strategy="uuid",name="hibernate-uuid")
	@GeneratedValue(generator="hibernate-uuid")
	@Column(length=32)
	private String id;
	@Column(length=10)
	private String c3;
	@Column(length=50)
	private String c4;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getC3() {
		return c3;
	}
	public void setC3(String c3) {
		this.c3 = c3;
	}
	public String getC4() {
		return c4;
	}
	public void setC4(String c4) {
		this.c4 = c4;
	}
	
	
}
