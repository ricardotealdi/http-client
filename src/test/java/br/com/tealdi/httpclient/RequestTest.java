package br.com.tealdi.httpclient;

import org.junit.Assert;
import org.junit.Test;

public class RequestTest {

	@Test
	public void shouldCreateARequest() {
		Header header = new Header();
		header.add("Content-Type", "text/plain");
		
		String body = "body";
		String uri = "my-uri";
		
		Request request = new Request(uri, body, header);
		
		Assert.assertEquals(uri, request.getUri()); 
		Assert.assertEquals(body, request.getBody()); 
		Assert.assertEquals(header, request.getHeader()); 
	}
}
