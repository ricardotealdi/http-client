package br.com.tealdi.httpclient.builder;

import br.com.tealdi.httpclient.Header;
import br.com.tealdi.httpclient.Request;

public class RequestBuilder implements ABuilderForRequest {
	private String uri;
	private Header header;
	private String body;
	
	public RequestBuilder() {
		uri = "";
		header = new Header();
		body = "";
	}
	
	@Override
	public ABuilderForRequest forThis(String uri) {
		this.uri = uri;
		return this;
	}
	
	@Override
	public ABuilderForRequest withBody(String body) {
		this.body = body;
		return this;
	}
	
	@Override
	public ABuilderForRequest withHeaderProperty(String headerPropertyName, String headerPropertyValue) {
		this.header.add(headerPropertyName, headerPropertyValue);
		return this;
	}
	
	@Override
	public ABuilderForRequest withHeader(Header header) {
		this.header = header;
		return this;
	}
	
	@Override
	public Request instance() {
		return new Request(uri, body, header);
	}
}
