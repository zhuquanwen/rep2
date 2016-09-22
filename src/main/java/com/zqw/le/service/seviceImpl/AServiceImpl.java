package com.zqw.le.service.seviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zqw.le.domain.p.moreTS.A;
import com.zqw.le.domain.p.moreTS.ADao;
import com.zqw.le.service.AService;
@Service
public class AServiceImpl extends BaseServiceImpl<A, ADao> implements AService{
	@Autowired
	private ADao aDao;
	@Override
	public List<A> findAs() {
		
		return aDao.findAs();
	}

}
