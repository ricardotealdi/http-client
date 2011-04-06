package br.com.tealdi.httpclient;

import java.net.HttpURLConnection;

public class Response {

	private final int statusCode;

	public Response(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public boolean success() {
		return statusCode == HttpURLConnection.HTTP_OK;
	}

}
