package service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dto.HttpDTO;
import service.HttpService;

public class HttpServiceImpl implements HttpService {

	String http;
	List<String> params;

	String method = DEFAULT_METHOD;
	Map<String, String> propertys;

	HttpURLConnection conn;
	InputStreamReader isr;
	BufferedReader br;

	public HttpServiceImpl() {
	}

	public HttpServiceImpl(String http) {
		this.http = http;
		this.params = new LinkedList<>();
		this.propertys = new LinkedHashMap<>();
	}

	@Override
	public void setHttp(String http) {
		// TODO Auto-generated method stub
		this.http = http;
		this.params = new LinkedList<>();
		this.propertys = new LinkedHashMap<>();
	}

	@Override
	public void setMethod(String method) {
		// TODO Auto-generated method stub
		this.method = method.toUpperCase();
	}

	@Override
	public void addParam(String key, int value) {
		// TODO Auto-generated method stub
		addParam(key, Integer.toString(value), DEFAULT_CHARSET);
	}

	@Override
	public void addParam(String key, String value) {
		// TODO Auto-generated method stub
		addParam(key, value, DEFAULT_CHARSET);
	}

	@Override
	public void addParam(String key, String value, Charset charset) {
		// TODO Auto-generated method stub
		params.add((params.isEmpty() ? "?" : "&") + URLEncoder.encode(key, charset) + "="
				+ URLEncoder.encode(value, charset));
	}

	@Override
	public void addProperty(String key, String value) {
		// TODO Auto-generated method stub
		propertys.put(key, value);
	}

	@Override
	public HttpDTO getConn() {
		// TODO Auto-generated method stub
		try {
			StringBuilder request = new StringBuilder(http);
			for (String param : params) {
				request.append(param);
			}

			URL url = new URL(request.toString());
			conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod(method);
			for (Map.Entry<String, String> entry : propertys.entrySet()) {
				conn.setRequestProperty(entry.getKey(), entry.getValue());
			}

			int responseCode = conn.getResponseCode();
			System.out.println(request + " 연결 결과 : " + responseCode);
			
			if (responseCode >= 200 && responseCode <= 300) {
				isr = new InputStreamReader(conn.getInputStream(), "UTF-8");
				br = new BufferedReader(isr);
			} else {
				isr = new InputStreamReader(conn.getErrorStream());
				br = new BufferedReader(isr);
			}

			StringBuffer buffer = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}
			String responseText = buffer.toString();

			System.out.println(responseText);
			return new HttpDTO(responseCode, responseText);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (isr != null) {
					isr.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public InputStream getConnStream() {
		// TODO Auto-generated method stub
		try {
			StringBuilder request = new StringBuilder(http);
			for (String param : params) {
				request.append(param);
			}

			URL url = new URL(request.toString());
			conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod(method);
			for (Map.Entry<String, String> entry : propertys.entrySet()) {
				conn.setRequestProperty(entry.getKey(), entry.getValue());
			}

			int responseCode = conn.getResponseCode();
			System.out.println(request + " 연결 결과 : " + responseCode);
			
			if (responseCode >= 200 && responseCode <= 300) {
				return conn.getInputStream();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}

	@Override
	public void disConn() {
		// TODO Auto-generated method stub
		if(conn != null) {
			conn.disconnect();
		}
	}
}
