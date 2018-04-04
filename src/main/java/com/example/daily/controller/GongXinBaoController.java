package com.example.daily.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.zip.GZIPInputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: zhiyuan
 * @date: 2017-12-11
 * @project: center
 * @description:
 */

@Controller("GongXinBaoController")
@RequestMapping(value = "gxbcallback")
public class GongXinBaoController {
	final static String ossFileName = "dataFromGx4Gzip";

	final String failedCode = "{\"retCode\": 2,\"retMsg\": \"失败\"}";
	final String successCode = "{\"retCode\": 1,\"retMsg\": \"成功\"}";


	/**
	 * write into file from gzip
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public String uncompressToString(InputStream inputStream) throws IOException {
		InputStream gzipInputStream = new GZIPInputStream(inputStream, 1024 * 1024);
		Reader reader = new InputStreamReader(gzipInputStream, "UTF-8");
		char[] buffer = new char[10240];
		StringWriter writer = new StringWriter();
		for (int length = 0; (length = reader.read(buffer)) > 0;) {
			writer.write(buffer, 0, length);
		}
		writer.close();
		reader.close();
		gzipInputStream.close();
		inputStream.close();
		return writer.toString();
	}
}
