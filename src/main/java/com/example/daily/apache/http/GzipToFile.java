package com.example.daily.apache.http;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/**
 * @author: zhiyuan
 * @date: 2018-03-06
 * @project: spring-boot-demo
 * @description:
 */

public class GzipToFile {

	public static void main(String args[]) {
		GzipToFile zipObj = new GzipToFile();
		zipObj.gzipMyFile();
	}

	public void gzipMyFile() {
		byte[] buffer = new byte[1024];
		try {
			// Specify Name and Path of Output GZip file here
			GZIPOutputStream gos = new GZIPOutputStream(new FileOutputStream("/home/zjz/Desktop/myFile" + ".gz"));
			// Specify the input file here
			FileInputStream fis = new FileInputStream("myNewFile.txt");
			// Read from input file and write to output GZip file
			int length;
			// fis.read(buffer)， 结果时Buffer有了内容，同时返回读取内容的长度，读到文件末尾时读取内容的长度变为-1
			while ((length = fis.read(buffer)) > 0) {
				/*
				 * public void write(byte[] buf, int off, int len): Write array of bytes to the
				 * compressed output stream. This method will block until all the bytes are
				 * written. Parameter: buf - the data to written off - the start offset of the
				 * data len - the length of the data
				 */
				gos.write(buffer, 0, length);
			}
			fis.close();
			/*
			 * public void finish(): Finish writing compressed data to the output stream
			 * without closing the underlying stream
			 */
			gos.finish();
			gos.close();
			System.out.println("File Compressed!!");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}