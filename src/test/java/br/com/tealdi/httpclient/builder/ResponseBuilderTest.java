package br.com.tealdi.httpclient.builder;

import junit.framework.Assert;

import org.junit.Test;

import br.com.tealdi.httpclient.Response;

public class ResponseBuilderTest {
	
	private int statusCode;
	private String body;

	@Test
	public void shouldBuildAResponse() {
		body = "body content";
		
		IResponseBuilder builder = new ResponseBuilder();
		Response response = builder.buildWith(statusCode, body);
		
		Assert.assertEquals(statusCode, response.getStatusCode());
		Assert.assertEquals(body, response.getBody());
	}
}
