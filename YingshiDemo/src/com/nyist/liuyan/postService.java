package com.nyist.liuyan;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class postService {

	public static boolean save(String text) throws Exception {

	String path="http://10.254.1.62/YingshiWebDemo/YingShiServlet";
		
	Map<String, String> params=new HashMap<String, String>();
	params.put("text", text);
		return SendPOSTRequest(path,params,"UTF-8");
	}

	private static boolean SendPOSTRequest(String path,
			Map<String, String> params, String encoding) throws Exception {
 
		StringBuilder sb=new StringBuilder();
		if (params!=null&&!params.isEmpty()) {
			for (Map.Entry<String, String> entry:params.entrySet()) {
				
				sb.append(entry.getKey()).append("=");
				sb.append(URLEncoder.encode(entry.getValue(),encoding));
				sb.append("&");
			}
			sb.deleteCharAt(sb.length()-1);
			
		}
    
		byte[] data=sb.toString().getBytes();
		HttpURLConnection connection=(HttpURLConnection) new URL(path).openConnection();
		connection.setConnectTimeout(5000);
		//允许对外连接数据
		connection.setDoOutput(true);
		
		connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		connection.setRequestProperty("Content-Length", data.length+"");
		OutputStream outputStream =connection.getOutputStream();
		outputStream.write(data);
		outputStream.flush();
		if (connection.getResponseCode()==200) {
			return true;
		}
		return false;
	}

}
