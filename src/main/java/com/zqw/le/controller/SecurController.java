package com.zqw.le.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqw.le.domain.p.secur.MyUser;
import com.zqw.le.domain.p.secur.Resource;
import com.zqw.le.domain.p.secur.Role;
import com.zqw.le.service.MyUserService;
import com.zqw.le.service.ResourceService;
import com.zqw.le.service.RoleService;

@Controller
@RequestMapping("secur")
public class SecurController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private MyUserService myUserService;
	@Autowired
	private ResourceService resourceService;
	
	
	@RequestMapping("/insertResource")
	@ResponseBody
	public Object insertResource(){
		Resource r=new Resource();
		r.setUrl("www.sina.com");
		r.setDescription("测试3");
		resourceService.save(r);
		return null;
	}
	
	@RequestMapping("/insertRole")
	@ResponseBody
	public Object insertRole(){
		Role role=new Role();
		role.setName("超级管理员");
		List<Resource> resource=resourceService.findAll();
		role.setResources(resource);
		roleService.save(role);
		return null;
	}
	
	@RequestMapping("/insertUser")
	@ResponseBody
	public Object insertUser(){
		MyUser user = new MyUser();
		List<Role> roles=roleService.findAll();
		user.setName("zhuquanwen");
		user.setEmail("quanwen@sina.cn");
		user.setRoles(roles);
		myUserService.save(user);
		return null;
	}
	
	@RequestMapping("/updateResource")
	@ResponseBody
	public Object updateResource(){
		Resource resource=resourceService.findOne("402881ea57127b2e0157127bd81a0000");
		resource.setDescription("这是一个地址");
		resourceService.save(resource);
		return null;
	}
	
	@RequestMapping("/updateRole")
	@ResponseBody
	public Object updateRole(){
		Role role=roleService.findOne("402881ea57127fbd0157128021fc0000");
		role.getResources().get(0).setDescription("测试1");
		role.getResources().get(1).setDescription("测试1");//测试修改关联的资源表信息
		role.getResources().remove(0);//测试删除和资源的关联关系
		roleService.save(role);
		return null;
	}
	@RequestMapping("/updateUser")
	@ResponseBody
	public Object updateUser(){
		MyUser user=myUserService.findAll().get(0);
		Role role=roleService.findAll().get(0);
		user.getRoles().add(role);
		myUserService.save(user);//测试修改关联角色信息。 中间表加入记录
		
//		List<Role> rs=user.getRoles(); //测试删除关联的角色信息
//		if(rs.size()!=0){
//			rs.remove(0);
//		}
		myUserService.save(user);
		
		return null;
	}
	
	@RequestMapping("/removeResource")
	@ResponseBody
	public Object removeResource(){
		Resource r=resourceService.findOne("402881ea5712a208015712a292390000");
		resourceService.delete(r);
		return null;
	}
	
	
}
