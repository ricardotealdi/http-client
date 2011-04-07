package br.com.tealdi.httpclient;

public class Request {

	private final String uri;
	private final Header header;
	private final String body;
	
	public Request(String uri) {
		this(uri, "", new Header());
	}
	
	public Request(String uri, String body) {
		this(uri, body, new Header());
	}
	
	public Request(String uri, Header header) {
		this(uri, "", header);
	}
	
	public Request(String uri, String body, Header header) {
		this.uri = uri;
		this.body = body;
		this.header = header;
	}

	public String getUri() {
		return uri;
	}

	public Header getHeader() {
		return header;
	}

	public String getBody() {
		return body;
	}
	
}
