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
		
		// ���� CloseableHttpClient ����
		CloseableHttpClient createDefault = HttpClients.createDefault();
		// ����һ�� URL ����URL ������Ը�������
		URIBuilder uriBuilder = new URIBuilder(url);
		uriBuilder.addParameter(parameter, value);
		// ����һ�� GET ����
		HttpGet httpGet = new HttpGet(uriBuilder.build());
		String string = "false";
			
		// ִ������
		CloseableHttpResponse execute = createDefault.execute(httpGet);
		// �����Ӧ״̬��
		int statusCode = execute.getStatusLine().getStatusCode();
		if(statusCode == 200) {
			
			// ȡ����Ӧ����
			HttpEntity entity = execute.getEntity();
			// �����ݷ��� String ����
			string = EntityUtils.toString(entity, "UTF-8");
			// �ر�
			execute.close();
			createDefault.close();
			System.out.println(string);
			
		}
		
	}
	
	public void doPost() throws Exception {
		
		// ���� CloseableHttpClient ����
		CloseableHttpClient createDefault = HttpClients.createDefault();
		// ����һ�� URL ����URL ������Ը�������
		ArrayList<BasicNameValuePair> arrayList = new ArrayList<BasicNameValuePair>();
		arrayList.add(new BasicNameValuePair("username", "zs"));
		arrayList.add(new BasicNameValuePair("password", "123456"));
		UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(arrayList, "UTF-8");
		// ����һ�� POST ����
		HttpPost httpPost = new HttpPost("https://www.baidu.com");
		httpPost.setEntity(urlEncodedFormEntity);
		String string = "false";
			
		// ִ������
		CloseableHttpResponse execute = createDefault.execute(httpPost);
		// �����Ӧ״̬��
		int statusCode = execute.getStatusLine().getStatusCode();
		if(statusCode == 302 || statusCode == 200) {
			
			// ȡ����Ӧ����
			HttpEntity entity = execute.getEntity();
			// �����ݷ��� String ����
			string = EntityUtils.toString(entity, "UTF-8");
			// �ر�
			execute.close();
			createDefault.close();
			System.out.println(string);
			
		}
		
	}
	
}
