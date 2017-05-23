package spider;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class HttpRequestUtils {
	public static String sendGet(String url, String param, Map<String, String> requestPropertys) throws Exception {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url;
			if(param!=null&&"".equals(param))
				urlNameString = urlNameString + "?" + param;
			URL realUrl = new URL(urlNameString);
			URLConnection connection = realUrl.openConnection();
			if(requestPropertys != null) { 
				Iterator<Map.Entry<String, String>> iter = requestPropertys.entrySet().iterator();
				while(iter.hasNext()) {
					Map.Entry<String, String> entry = iter.next();
					connection.setRequestProperty(entry.getKey(), entry.getValue());	
				}
			}
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			throw e;
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	public static String sendPost(String url, String param,
			Map<String, String> requestPropertys) throws Exception {
		BufferedWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);

			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			if(requestPropertys != null) { 
				Iterator<Map.Entry<String, String>> iter = requestPropertys.entrySet().iterator();
				while(iter.hasNext()) {

					Map.Entry<String, String> entry = iter.next();
					conn.setRequestProperty(entry.getKey(), entry.getValue());	
				}
			}
			conn.setDoOutput(true);
			conn.setDoInput(true);

			conn.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.0.5) Gecko/2008120122 Firefox/3.0.5");
			if (param != null) {
				conn.setRequestProperty("Content-Length",
						String.valueOf(param.length()));
			}
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.setRequestProperty("Accept", "*/*");
			conn.setRequestMethod("POST");
			conn.connect();

			out = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(),"utf-8"));

			out.write(param == null ? "" : param);

			out.flush();

			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result = result + line + "\n";
			}
		} catch (Exception e) {
			System.out.println("HttpRequestUtils sendPost() error:");
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				throw ex;
			}
		}
		return result;
	}
	
	public static String sendPost1(String url, String param,
			Map<String, String> requestPropertys) throws Exception {
		BufferedWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);

			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			if(requestPropertys != null) { 
				Iterator<Map.Entry<String, String>> iter = requestPropertys.entrySet().iterator();
				while(iter.hasNext()) {

					Map.Entry<String, String> entry = iter.next();
					conn.setRequestProperty(entry.getKey(), entry.getValue());	
				}
			}
			conn.setDoOutput(true);
			conn.setDoInput(true);

			conn.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.0.5) Gecko/2008120122 Firefox/3.0.5");
			if (param != null) {
				conn.setRequestProperty("Content-Length",
						String.valueOf(param.length()));
			}
			conn.setRequestProperty("Content-Type",
					"application/json;charset=utf-8");
			conn.setRequestProperty("Accept", "*/*");
			conn.setRequestMethod("POST");
			conn.connect();

			out = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(),"utf-8"));

			out.write(param == null ? "" : param);

			out.flush();

			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result = result + line + "\n";
			}
		} catch (Exception e) {
			System.out.println("HttpRequestUtils sendPost() error:");
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				throw ex;
			}
		}
		return result;
	}
	public static void main(String[] args) throws Exception {
	/*	Map<String,String>map=new HashMap<String,String>();
		Long timeS=System.currentTimeMillis()/1000;
		String x_rio_seq="ab570e0a:015a1d0fbef0:00cb3d";
		String appSecret ="V8iHDLMDfPQjRHABa2IWsumQftMf964tNW1sDUXpqvIeGUNaxtjq";
		TestEncypt test=new TestEncypt();
		  String token=	
		map.put(key, value)
		map.put(key, value)
		map.put(key, value)
		sendPost1("http://172.17.20.192:8074/api/staff/get","{\"staffName\": \"test1\"}",null);*/
	}
	
}