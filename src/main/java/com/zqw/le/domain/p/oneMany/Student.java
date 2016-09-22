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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

@Entity
@Table(name="student1")

public class Student implements Serializable{
	@Id
	@GenericGenerator(name="hibernate-uuid", strategy="uuid")
	@GeneratedValue(generator="hibernate-uuid")
	@Column(length=32)
	private String id;
	@Column(length=50)
	private String name;
	
	@Column(length=32,nullable=false,unique=true)
	@Index(name="numIndex")
	private String num;
	@OneToMany(mappedBy="student",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Set<Book> books;
	
	@OneToOne(mappedBy="student",cascade=CascadeType.ALL,fetch=FetchType.LAZY,optional=true)
	private Team team;
	
	@ManyToMany(cascade={CascadeType.MERGE,CascadeType.PERSIST},fetch=FetchType.LAZY)
	@JoinTable(name="student_author",joinColumns={@JoinColumn(name="stu_id")},inverseJoinColumns={@JoinColumn(name="au_id")})
	private Set<Author> authors=new HashSet<Author>();
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNum() {
		return num;
	}
	
	public void setNum(String num) {
		this.num = num;
	}
	public Set<Book> getBooks() {
		return books;
	}
	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public Set<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}
	
	
	
	
}
