package com.zqw.le.conditional;

public class LinuxListService implements ListService{

	@Override
	public String showCmd() {
		return "ls";
	}

}
