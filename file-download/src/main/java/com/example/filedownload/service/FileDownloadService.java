package com.example.filedownload.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2022/10/17 13:31
 */
@Service
public class FileDownloadService {

	public void download(HttpServletResponse response) {
		try {


			File file = new File("/Users/demussong/Desktop/local/记录.pdf");
//			response.setHeader("Content-Disposition",
//					"attachment; filename=" + URLEncoder.encode(file.getName(), "UTF-8") + ";filename*=UTF-8''"
//							+ URLEncoder.encode(file.getName(), "UTF-8"));
//			response.setContentType("application/octet-stream");
			FileInputStream in = new FileInputStream(file);
			StreamUtils.copy(in, response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
