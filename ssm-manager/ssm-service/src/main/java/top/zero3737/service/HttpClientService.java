package top.zero3737.service;

import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;



public class HttpClientService {

	public void doGet(String url, String parameter, String value) throws Exception {
		
		// 创建 CloseableHttpClient 对象
		CloseableHttpClient createDefault = HttpClients.createDefault();
		// 创建一个 URL 对象，URL 对象可以附带参数
		URIBuilder uriBuilder = new URIBuilder(url);
		uriBuilder.addParameter(parameter, value);
		// 创建一个 GET 对象
		HttpGet httpGet = new HttpGet(uriBuilder.build());
		String string = "false";
			
		// 执行请求
		CloseableHttpResponse execute = createDefault.execute(httpGet);
		// 获得响应状态码
		int statusCode = execute.getStatusLine().getStatusCode();
		if(statusCode == 200) {
			
			// 取得响应内容
			HttpEntity entity = execute.getEntity();
			// 将内容返回 String 类型
			string = EntityUtils.toString(entity, "UTF-8");
			// 关闭
			execute.close();
			createDefault.close();
			System.out.println(string);
			
		}
		
	}
	
	public void doPost() throws Exception {
		
		// 创建 CloseableHttpClient 对象
		CloseableHttpClient createDefault = HttpClients.createDefault();
		// 创建一个 URL 对象，URL 对象可以附带参数
		ArrayList<BasicNameValuePair> arrayList = new ArrayList<BasicNameValuePair>();
		arrayList.add(new BasicNameValuePair("username", "zs"));
		arrayList.add(new BasicNameValuePair("password", "123456"));
		UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(arrayList, "UTF-8");
		// 创建一个 POST 对象
		HttpPost httpPost = new HttpPost("https://www.baidu.com");
		httpPost.setEntity(urlEncodedFormEntity);
		String string = "false";
			
		// 执行请求
		CloseableHttpResponse execute = createDefault.execute(httpPost);
		// 获得响应状态码
		int statusCode = execute.getStatusLine().getStatusCode();
		if(statusCode == 302 || statusCode == 200) {
			
			// 取得响应内容
			HttpEntity entity = execute.getEntity();
			// 将内容返回 String 类型
			string = EntityUtils.toString(entity, "UTF-8");
			// 关闭
			execute.close();
			createDefault.close();
			System.out.println(string);
			
		}
		
	}
	
}
