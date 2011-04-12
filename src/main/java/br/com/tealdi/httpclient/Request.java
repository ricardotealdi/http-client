package br.com.tealdi.httpclient;

public class Request {

	private final String uri;
	private final Header header;
	private final String body;
	private String charsetEncoding;
	
	public Request(String uri) {
		this(uri, "", new Header(), "");
	}
	
	public Request(String uri, String body) {
		this(uri, body, new Header(), "");
	}
	
	public Request(String uri, Header header) {
		this(uri, "", header, "");
	}
	
	public Request(String uri, String body, Header header, String charsetEncoding) {
		this.uri = uri;
		this.body = body;
		this.header = header;
		if(charsetEncoding == null || charsetEncoding.isEmpty()) {
			this.charsetEncoding = "UTF-8";
		} else {
			this.charsetEncoding = charsetEncoding;
		}
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

	public void setCharsetEncoding(String charsetEncoding) {
		this.charsetEncoding = charsetEncoding;
	}

	public String getCharsetEncoding() {
		return charsetEncoding;
	}
	
}
