package br.com.tealdi.httpclient;


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
		return StatusCode
					.getSuccessfulStatusCodes()
					.contains(getStatusCode());
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
