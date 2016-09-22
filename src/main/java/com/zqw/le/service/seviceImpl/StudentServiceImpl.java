package com.zqw.le.service.seviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zqw.le.domain.p.oneMany.Student;
import com.zqw.le.domain.p.oneMany.StudentDao;
import com.zqw.le.service.StudentService;
@Service
public class StudentServiceImpl extends BaseServiceImpl<Student, StudentDao> implements StudentService{
	
}
