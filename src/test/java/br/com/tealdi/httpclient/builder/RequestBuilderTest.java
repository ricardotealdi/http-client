package br.com.tealdi.httpclient.builder;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.com.tealdi.httpclient.Header;
import br.com.tealdi.httpclient.Request;

public class RequestBuilderTest {
	
	private ABuilderForRequest builder;

	@Before
	public void setUp() {
		builder = new RequestBuilder();
	}
	
	@Test
	public void shouldCreateARequestWithAUri() {
		String uri = "any-uri";
		
		Request request = builder.forThis(uri).instance();
		
		Assert.assertEquals(uri, request.getUri());
	}
	
	@Test
	public void shouldCreateARequestWithABody() {
		String body = "any-body";
		
		Request request = builder.withBody(body).instance();
		
		Assert.assertEquals(body, request.getBody());
	}
	
	@Test
	public void shouldCreateARequestWithAHeader() {
		Header header = new Header();
		
		Request request = builder.withHeader(header).instance();
		
		Assert.assertEquals(header, request.getHeader());
	}
	
	@Test
	public void shouldCreateARequestWithSomeHeaderProperties() {
		String contentTypeValue = "text/plain";
		String acceptValue = "text/html";
		
		Request request = 
			builder
				.withHeaderProperty("Content-Type", contentTypeValue)
				.withHeaderProperty("Accept", acceptValue)
				.instance();
		
		Assert.assertEquals(contentTypeValue, request.getHeader().get("Content-Type"));
		Assert.assertEquals(acceptValue, request.getHeader().get("Accept"));
		Assert.assertEquals(2, request.getHeader().all().size());
	}
}
