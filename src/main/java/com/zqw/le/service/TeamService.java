package com.zqw.le.service;

import java.util.List;

import com.zqw.le.domain.p.oneMany.Team;
import com.zqw.le.domain.p.oneMany.TeamDao;

public interface TeamService extends BaseService<Team, TeamDao>{
	List<Team> findByStudentName(String name);
}
