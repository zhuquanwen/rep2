package com.zqw.le.conditional;

public class WindowsListService implements ListService{

	@Override
	public String showCmd() {
		return "dir";
	}

}
