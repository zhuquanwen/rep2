package com.zqw.le.service;

import java.util.List;

import com.zqw.le.domain.p.moreTS.A;
import com.zqw.le.domain.p.moreTS.ADao;

public interface AService extends BaseService<A, ADao>{
	public List<A> findAs();
}
