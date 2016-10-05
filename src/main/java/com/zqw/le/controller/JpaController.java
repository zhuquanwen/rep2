package com.zqw.le.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zqw.le.aop.Log;
import com.zqw.le.aop.OperateType;
import com.zqw.le.domain.p.TestTable1;
import com.zqw.le.domain.p.TestTable1Repository;
import com.zqw.le.domain.p.TestUuid;
import com.zqw.le.domain.p.TestUuidRepository;
import com.zqw.le.domain.p.jpa.CustomerSpecs;
import com.zqw.le.domain.p.oneMany.Author;
import com.zqw.le.domain.p.oneMany.Book;
import com.zqw.le.domain.p.oneMany.Student;
import com.zqw.le.domain.p.oneMany.Team;
import com.zqw.le.domain.p.secur.Role;
import com.zqw.le.domain.s.TestTable2;
import com.zqw.le.domain.s.TestTable2Repository;
import com.zqw.le.jpaExpand.Criteria;
import com.zqw.le.jpaExpand.Restrictions;
import com.zqw.le.service.AuthorService;
import com.zqw.le.service.BookService;
import com.zqw.le.service.RoleService;
import com.zqw.le.service.StudentService;
import com.zqw.le.service.TeamService;
import com.zqw.le.service.TestTable1Service;

@RestController
public class JpaController {
	@Autowired
	private TestTable1Repository testTable1Repository;
	@Autowired
	private TestTable2Repository testTable2Repository;
	@Autowired
	private TestUuidRepository testUuidRepository;
	@Autowired
	private TestTable1Service testTable1Service;
	@Autowired
	private StudentService studentService;
	@Autowired
	private BookService bookService;
	@Autowired
	private TeamService teamService;
	@Autowired
	private AuthorService authorService;
	@Autowired
	private RoleService roleService;
	
	
	/**主数据库查询*/
	@RequestMapping("/getTestTable1")
	public String getUser(String name){
		TestTable1 tt1=testTable1Repository.findByName(name);
		return tt1.toString();
	}
	
	/**主数据库插入*/
	@RequestMapping(value="/insertTestTable1/{name}",method=RequestMethod.GET)
	public TestTable1 insertUser(@PathVariable String name){
		TestTable1 user=new TestTable1(name);
		testTable1Repository.save(user);
		return user;
	}
	/**副数据库查询*/
	@RequestMapping("/getTestTable2/{name}")
	public TestTable2 getTestTable2(@PathVariable String name){
		TestTable2 tt2=testTable2Repository.findTable2(name);
		return tt2;
	}
	/**副数据库插入*/
	@RequestMapping("/insertTestTable2/{name}/{age}")
	public TestTable2 insertTestTable2(@PathVariable String name,@PathVariable Integer age){
		TestTable2 tt2=new TestTable2(name, age);
		testTable2Repository.save(tt2);
		return tt2;
	}
	/**JPA like语句测试*/
	@RequestMapping("/getTestTable2Like/{name}")
	public TestTable2 getTestTable2Like(@PathVariable String name){
		TestTable2 tt2=testTable2Repository.findByNameLike("%"+name+"%");
		return tt2;
	}
	
	/**JPA ?号注入参数测试*/
	@RequestMapping("/testJpaWenhao")
	public TestTable1 testJpaWenhao(String name){
		TestTable1 tt1=testTable1Repository.findTT1(name);
		return tt1;
	}
	
	/**JPA修改的query测试用modifying注解修饰*/
	@RequestMapping("/testJpaModify/{name}")
	public TestTable1 testJpaModify(@PathVariable String name){
		TestTable1 tt1=testTable1Repository.findOne(1L);
		testTable1Repository.updateTestTable1Name(name);
		return tt1;
	}
	
	/**JPA namequery查询 测试*/
	@RequestMapping("/testJpaNameQuery/{name}")
	public TestTable1 testJpaNameQuery(@PathVariable String name){
		TestTable1 tt1=testTable1Repository.searchName(name);
		return tt1;
	}
	
	/**JPA namedNativeQuery查询测试,返回一个object[]*/
	@RequestMapping("/testJpaNamedNativeQuery")
	public Object[] testJpaNamedNativeQuery(){
		return testTable1Repository.testNativeQueryName();
	}

	
	/**JPA namedNativeQuery查询测试,返回一个实体*/
	@Log(name="本地查询",remark="测试本地查询",operateType=OperateType.SELECT)
	@RequestMapping("/testJpaNamedQuery")
	public TestTable1 testJpaNamedQuery(){
		return testTable1Repository.testNativeQuery();
	}
	
	//JPA 测试UUID
	@Log(name="插入TestUuid",remark="测试插入UUID",operateType=OperateType.ADD)
	@RequestMapping("testInsertTestUuid/{name}")
	public TestUuid testInsertTestUuid(@PathVariable String name){
		TestUuid tu=new TestUuid();
		tu.setCreateTime(new Timestamp(System.currentTimeMillis()));
		tu.setName(name);
		testUuidRepository.save(tu);
		return tu;
	}
	
	//JPA测试简单的分页
	@RequestMapping("/testJpaPage")
	public List<TestTable1> TestJpaPage(){
		Pageable page = new PageRequest(0, 5);
		Page<TestTable1> tt1Page =  testTable1Repository.findByNameLike("%quan%", page);
		return tt1Page.getContent();
		
	}
	//JPA测试本地方法
	@RequestMapping("/testJpaNative/{name}")
	public List<TestTable1> testJpaNative(@PathVariable String name){
		return testTable1Repository.findByNameNative(name);
	}
	//测试JPA动态查询扩展
	@RequestMapping("/testJpaCir")
	public Object testJpaCir(){
		Object obj=null;
		Criteria<TestTable1> c = new Criteria<TestTable1>();  
		c.add(Restrictions.like("name", "%zhuquanwen%", true));  
		       /* c.add(Restrictions.eq("level", searchParam.getLevel(), false));  
		        c.add(Restrictions.eq("mainStatus", searchParam.getMainStatus(), true));  
		        c.add(Restrictions.eq("flowStatus", searchParam.getFlowStatus(), true));  
		        c.add(Restrictions.eq("createUser.userName", searchParam.getCreateUser(), true));  
		        c.add(Restrictions.lte("submitTime", searchParam.getStartSubmitTime(), true));  
		        c.add(Restrictions.gte("submitTime", searchParam.getEndSubmitTime(), true));  
		        c.add(Restrictions.eq("needFollow", searchParam.getIsfollow(), true));  
		        c.add(Restrictions.ne("flowStatus", CaseConstants.CASE_STATUS_DRAFT, true));  
		        c.add(Restrictions.in("solveTeam.code",teamCodes, true));  */
		List<TestTable1> tt1s= testTable1Repository.findAll(c);  
		obj=tt1s;
		return obj;
	}
	
	//测试Jpa排序
	@RequestMapping("/testJpaSort")
	public Object testJpaSort(){
		Object obj=null;
		Criteria<TestTable1> c = new Criteria<TestTable1>();  
		c.add(Restrictions.like("name", "%zhuquanwen%", true));  
		      
		Sort s=new Sort(Direction.DESC, "id"); 
		Pageable page = new PageRequest(0, 4, s);
		
		Page<TestTable1> tt1s= testTable1Repository.findAll(c,page);  
		obj=tt1s;
		return obj;
	}
	
	//测试调用Service
	@RequestMapping("testJpaUseService")
	public Page<TestTable1> testJpaUseService(){
		Sort sort=new Sort(Direction.DESC, "name");
		Pageable page = new PageRequest(0, 5, sort);
 		Page<TestTable1> result=testTable1Service.findAll(page);
		return result;
	}
	
	//测试Jpa one to many save
	@RequestMapping("testJpaOneToM")
	public Object testJpaOneToM(){
		Student s=new Student();
		s.setName("zhuquanwen");
		String num=UUID.randomUUID().toString().substring(0, 32);
		s.setNum(num);
		studentService.save(s);
		Book book=new Book();
		book.setName("新华字典");
		book.setStudent(s);
		bookService.save(book);
		
		return null;
	}
	
	//测试Jpa one to many search
	@RequestMapping("testJpaOneToMSearch")
	public Set<Book> testJpaOneToMSearch(){
		List<Student> ss=studentService.findAll();
		Set<Book> books=ss.get(0).getBooks();
		return books;
	}
	
	//测试Jpa one to many search 通过关联字段
	@RequestMapping("testJpaOneToMSearch2")
	public Book testJpaOneToMSearch2(){
		Book book=bookService.findBookByStudent("5b34848e-4cb8-4d20-9d03-31cc04b8");
		return book;
	}
	
	//测试Jpa one to one save
	@RequestMapping("testJpaOneToOne")
	public Team testJpaOneToOne(){
		Team team=new Team();
		team.setArea("守门员");
		Student s=studentService.findOne("402881ea570469170157046a317f0000");
		team.setStudent(s);
		teamService.save(team);
		
		return team;
				
	}
	
	@RequestMapping("testJpaOneToOneFind")
	public List<Team> testJpaOneToOneFind(){
		List<Team> list=teamService.findByStudentName("zhuquanwen");
		
		return list;
	}
	@RequestMapping("testJpaManyToManySave")
	public String testJpaManyToManySave(){
		Student s=new Student();
		s.setName("zhuquanwen1");
		s.setNum("111111111");
		Author author=authorService.findOne("402881ea570f041501570f047ab40000");
		s.getAuthors().add(author);
		studentService.save(s);
		return "1111";
	}
	
	@RequestMapping("testJpaManyToManyDelete")
	public String testJpaManyToManyDelete(){
		Student s=studentService.findOne("402881ea570f106c01570f10ea8d0000");
		studentService.delete(s);
		return "1111";
	}
	
	@RequestMapping("testEhcache")
	public List<Role> testEhcache(){
		return roleService.findAll();
	}
	@RequestMapping("testEhcacheSave")
	public Role testEhcacheSave(){
		Role role =  roleService.findAll().get(0);
		role.getResources().get(0).setDescription(UUID.randomUUID().toString());
		roleService.save(role);
		return role;
	}
	//测试JPA的 speXXX
	@RequestMapping("testJpaSpe")
	public List<TestTable1> testJpaSpe(){
		return testTable1Repository.findAll(CustomerSpecs.test1TableSomething());
	}
	
	//测试JPA的 自定义CustomRepository
	@RequestMapping("testJpaCustomRe")
	public List<TestTable1> testJpaCustomRe(){
		return testTable1Repository.getMapResult("select * from test_table1");
	}
	
	//测试JPA的 自定义CustomRepository
	@RequestMapping(value="testJpaCustomRe1",produces={"application/json;charset=UTF-8"})
	public List<TestTable1> testJpaCustomRe1(){
		try{
		TestTable1 tt = new TestTable1();
		tt.setName("测试");
		List<TestTable1> tts =  testTable1Repository.findByAuto(tt);
		return tts;
		}catch(Exception e ){
			e.printStackTrace();
			return null;
		}		
	}
}
