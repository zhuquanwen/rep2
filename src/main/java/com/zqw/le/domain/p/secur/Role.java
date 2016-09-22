package com.zqw.le.domain.p.secur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="role")
public class Role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5564047491805920740L;
	@Id
	@GenericGenerator(strategy="uuid",name="hibernate-uuid")
	@GeneratedValue(generator="hibernate-uuid")
	@Column(length=32)
	private String id;
	@Column(length=32)
	private String name;
	@ManyToMany(mappedBy="roles",cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.LAZY)
	@JsonIgnore
	private List<MyUser> myUsers=new ArrayList<MyUser>();
	@ManyToMany(cascade={CascadeType.MERGE,CascadeType.PERSIST},fetch=FetchType.LAZY)
	@JoinTable(name="role_resource",joinColumns={@JoinColumn(name="role_id")},inverseJoinColumns={@JoinColumn(name="resource_id")})
	private List<Resource> resources = new ArrayList<Resource>();
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
	
	public List<MyUser> getMyUsers() {
		return myUsers;
	}
	public void setMyUsers(List<MyUser> myUsers) {
		this.myUsers = myUsers;
	}
	public List<Resource> getResources() {
		return resources;
	}
	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
	
	
	
}
