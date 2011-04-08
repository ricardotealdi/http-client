package br.com.tealdi.httpclient.builder;

import br.com.tealdi.httpclient.Header;
import br.com.tealdi.httpclient.Request;

public class RequestBuilder {
	private String uri;
	private Header header;
	private String body;
	
	public RequestBuilder() {
		uri = "";
		header = new Header();
		body = "";
	}
	
	public RequestBuilder withUri(String uri) {
		this.uri = uri;
		return this;
	}
	
	public RequestBuilder withBody(String body) {
		this.body = body;
		return this;
	}
	
	public RequestBuilder withHeaderProperty(String headerPropertyName, String headerPropertyValue) {
		this.header.add(headerPropertyName, headerPropertyValue);
		return this;
	}
	
	public RequestBuilder withHeader(Header header) {
		this.header = header;
		return this;
	}
	
	public Request instance() {
		return new Request(uri, body, header);
	}
}
