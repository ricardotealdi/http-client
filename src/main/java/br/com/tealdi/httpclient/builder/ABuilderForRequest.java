package br.com.tealdi.httpclient.builder;

import br.com.tealdi.httpclient.Header;
import br.com.tealdi.httpclient.Request;

public interface ABuilderForRequest {

	public abstract ABuilderForRequest forThis(String uri);

	public abstract ABuilderForRequest withBody(String body);

	public abstract ABuilderForRequest withHeaderProperty(
			String headerPropertyName, String headerPropertyValue);

	public abstract ABuilderForRequest withHeader(Header header);

	public abstract Request instance();

}