package br.com.tealdi.httpclient;

import java.net.HttpURLConnection;

public class Response {

	private final int statusCode;

	public Response(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public boolean success() {
		return 
			statusCode == HttpURLConnection.HTTP_OK
			|| statusCode == HttpURLConnection.HTTP_CREATED
			|| statusCode == HttpURLConnection.HTTP_ACCEPTED
			|| statusCode == HttpURLConnection.HTTP_NOT_AUTHORITATIVE
			|| statusCode == HttpURLConnection.HTTP_NO_CONTENT
			|| statusCode == HttpURLConnection.HTTP_RESET
			|| statusCode == HttpURLConnection.HTTP_PARTIAL;
	}

}
