package com.zqw.le.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("fileUpload")
public class FileUploadController {
	
	@RequestMapping("/toUpload")
	public String toUpload(){
		return "thymeleaf/fileUpload";
	}
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public @ResponseBody String upload(MultipartFile file) throws IOException{
		String result="";
		InputStream is=file.getInputStream();
		File fileNew=new File("D:/test"+file.getOriginalFilename());
		OutputStream os=new FileOutputStream(fileNew);
		IOUtils.copy(is, os);
		return result;
	}
}
