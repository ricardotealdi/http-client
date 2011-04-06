package br.com.tealdi.httpclient;

import java.net.HttpURLConnection;

public class Response {

	private final int statusCode;
	private final String body;
	private final Header header;

	public Response(int statusCode, String body, Header header) {
		this.statusCode = statusCode;
		this.body = body;
		this.header = header;
	}
	
	public boolean success() {
		return 
			getStatusCode() == HttpURLConnection.HTTP_OK
			|| getStatusCode() == HttpURLConnection.HTTP_CREATED
			|| getStatusCode() == HttpURLConnection.HTTP_ACCEPTED
			|| getStatusCode() == HttpURLConnection.HTTP_NOT_AUTHORITATIVE
			|| getStatusCode() == HttpURLConnection.HTTP_NO_CONTENT
			|| getStatusCode() == HttpURLConnection.HTTP_RESET
			|| getStatusCode() == HttpURLConnection.HTTP_PARTIAL;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getBody() {
		return body;
	}

	public Header getHeader() {
		return header;
	}
}
