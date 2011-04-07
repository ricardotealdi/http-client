package br.com.tealdi.httpclient;

import java.util.HashMap;

public class Header {

	private HashMap<String, String> headers;
	
	public Header() {
		headers = new HashMap<String, String>();
	}
	
	public void add(String key, String value) {
		all().put(key, value);
	}
	
	public HashMap<String, String> all() {
		return headers;
	}
	
	public String get(String key) {
		return all().get(key);
	}

	public HashMap<String, String> getHeaders() {
		return all();
	}
}
