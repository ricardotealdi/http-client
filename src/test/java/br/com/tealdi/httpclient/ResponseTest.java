package br.com.tealdi.httpclient;

import java.net.HttpURLConnection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ResponseTest {
	private Response response;
	
	@Before
	public void setUp() {

	}
	
	@Test
	public void shouldBeASuccessfulResponseWhenStatusCodeWereOk() {
		givenAnOkResponse();
		Assert.assertEquals(true, response.success());
	}
	
	private void givenAnOkResponse() {
		response = new Response(HttpURLConnection.HTTP_OK);
	}
}
