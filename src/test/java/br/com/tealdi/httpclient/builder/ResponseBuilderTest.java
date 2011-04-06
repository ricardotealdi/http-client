package br.com.tealdi.httpclient.builder;

import junit.framework.Assert;

import org.junit.Test;

import br.com.tealdi.httpclient.Response;

public class ResponseBuilderTest {
	
	private int statusCode; 

	@Test
	public void shouldBuildAResponse() {
		IResponseBuilder builder = new ResponseBuilder();
		Response response = builder.buildWith(statusCode);
		
		Assert.assertEquals(statusCode, response.getStatusCode());
	}
}
