package br.com.tealdi.httpclient.builder;

import junit.framework.Assert;

import org.junit.Test;

import br.com.tealdi.httpclient.Header;
import br.com.tealdi.httpclient.Response;

public class ResponseBuilderTest {
	
	private int statusCode;
	private String body;
	private Header header;

	@Test
	public void shouldBuildAResponse() {
		body = "body content";
		header = new Header();
		header.add("Content-Type", "text/plain");
		
		ABuilderForResponse builder = new ResponseBuilder();
		Response response = builder.buildWith(statusCode, body, header);
		
		Assert.assertEquals(statusCode, response.getStatusCode());
		Assert.assertEquals(body, response.getBody());
		Assert.assertEquals(header, response.getHeader());
	}
}
