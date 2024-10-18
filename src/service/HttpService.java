package service;

import java.io.InputStream;
import java.nio.charset.Charset;

import dto.HttpDTO;

public interface HttpService {
	
	String DEFAULT_METHOD = "GET";

	Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	
	public void setHttp(String http);
	
	public void setMethod(String method);

	public void addParam(String key, int value);

	public void addParam(String key, String value);

	public void addParam(String key, String value, Charset charset);
	
	public void addProperty(String key, String value);
	
	public HttpDTO getConn();
	
	public InputStream getConnStream();
	
	public void disConn();
}
