package com.bc.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;


@SuppressWarnings("deprecation")
public class HttpClientUtil {

	public static final Logger logger = Logger.getLogger(HttpClientUtil.class);



	public static String postWithParams(String url, String params)  {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String responseBody = "";
		HttpPost httppost = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build();
		httppost.setConfig(requestConfig);
		httppost.setHeader("Content-Type", "application/json;charset=UTF-8");
		logger.info(" http request url : " + url);
		try {
			String js= params;
			logger.info(" http request js : " + js);
			StringEntity se = new StringEntity(js,"utf-8");
			se.setContentType("text/json");
			se.setContentEncoding("utf-8");

			httppost.setEntity(se);
			CloseableHttpResponse response = httpclient.execute(httppost);
			logger.info(" http request status : " + response.getStatusLine());
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					responseBody = EntityUtils.toString(entity, "UTF-8");
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			logger.error(e);
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				logger.error(e);
			}
		}
		logger.info(" http request response : " + responseBody);
		return responseBody;
	}
	

	public static void main(String[] args) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userName", "hcs00611"));
		params.add(new BasicNameValuePair("pwd", "236547"));
		//logger.info("result>>>>>"+postWithParams("http://127.0.0.1:8080/2/tool/auth/test", params));
		//logger.info("result>>>>>"+postWithParams("http://127.0.0.1:8080/2/tool/auth/login", params));
	}
}
