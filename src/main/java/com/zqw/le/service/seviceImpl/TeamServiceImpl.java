package com.zqw.le.service.seviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zqw.le.domain.p.oneMany.Team;
import com.zqw.le.domain.p.oneMany.TeamDao;
import com.zqw.le.service.TeamService;
@Service
public class TeamServiceImpl extends BaseServiceImpl<Team, TeamDao> implements TeamService{
	@Autowired
	private TeamDao teamDao;
	@Override
	public List<Team> findByStudentName(String name) {
		
		return teamDao.findByStudentName(name);
	}

}
