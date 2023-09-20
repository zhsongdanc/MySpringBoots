package com.example.filedownload.controller;

import com.example.filedownload.service.FileDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2022/10/17 13:30
 */
@RestController
public class FileDownloadController {

	@Autowired
	FileDownloadService fileDownloadService;

	@RequestMapping("download")
	public void download(HttpServletResponse response) {
		fileDownloadService.download(response);
	}
}
