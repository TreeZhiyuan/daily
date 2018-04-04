package com.example.daily.apache.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.MediaType;

import com.example.daily.common.SystemProperty;

import net.sf.json.JSONObject;

/**
 * @author: zhiyuan
 * @date: 2018-02-09
 * @project: spring-boot-demo
 * @description:
 */

public class HttpClientTests {
	CloseableHttpClient httpClient = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		httpClient = HttpClients.createDefault();
	}

	@After
	public void tearDown() throws Exception {
		httpClient.close();
	}

	@Test
	public void testGetProperties() {
		String baseHost = SystemProperty.getProperty("request.baseHost");
		String apiUrl = SystemProperty.getProperty("request.apiUrl");
		System.out.println(baseHost + apiUrl);
	}

	@Test
	public void testGetRequest() {
		final String url = "http://www.baidu.com";
		/*
		 * 如果有参数可拼接到url后完成
		 */
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = null;
		String content = null;
		try {
			response = httpClient.execute(httpGet);
			content = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("\n\rcontent:<>\n\r" + content);
	}

	/**
	 * post gzip stream request with CloseableHttpClient
	 * 
	 * @param url
	 * @param message
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static void sendHttpGzip(String url, String message) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		// put request data into stream
		try {
			ByteArrayOutputStream originalContent = new ByteArrayOutputStream();
			originalContent.write(message.getBytes(Charset.forName("UTF-8")));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			GZIPOutputStream gzipOut = new GZIPOutputStream(baos);
			originalContent.writeTo(gzipOut);
			gzipOut.finish();
			httpPost.setEntity(new ByteArrayEntity(baos.toByteArray()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpResponse httpResponse = httpClient.execute(httpPost);
		System.out.println(EntityUtils.toString(httpResponse.getEntity()));
	}

	/**
	 * POST 请求，参数是JSON字符串
	 */
	@Test
	public void testPostWithJson() {
		JSONObject postJson = new JSONObject();
		postJson.put("userName", "AAAAAA");
		postJson.put("password", "PASSSSS");

		HttpPost httpPost = new HttpPost("http://localhost:8080/spring_boot_demo/api/users");
		HttpResponse response = null;
		httpPost.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
		try {
			httpPost.setEntity(new StringEntity(postJson.toString()));
			System.out.println("request parameters" + EntityUtils.toString(httpPost.getEntity()));

			response = httpClient.execute(httpPost);
			System.out.println(" code:" + response.getStatusLine().getStatusCode());
			System.out.println("doPostForInfobipUnsub response" + response.getStatusLine().toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	@Test
	public void testPostRequestWithFormUrlencoded() {
		final String url = "http://localhost:8080/lqmall_admin/admin/signIn", encoding = "utf-8";
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse response = null;
		// 设置请求header
		httpPost.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);

		// 装填参数
		List<NameValuePair> params = new ArrayList<NameValuePair>() {
			private static final long serialVersionUID = 1L;

			{
				add(new BasicNameValuePair("name", "admin"));
				add(new BasicNameValuePair("password", "123456"));
			}
		};

		try {
			// 设置参数到请求对象中
			httpPost.setEntity(new UrlEncodedFormEntity(params, encoding));
			response = httpClient.execute(httpPost);
			System.out.println("postReq: " + EntityUtils.toString(response.getEntity()));
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
