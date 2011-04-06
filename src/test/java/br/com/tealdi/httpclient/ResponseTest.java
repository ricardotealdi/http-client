package br.com.tealdi.httpclient;

import java.net.HttpURLConnection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ResponseTest {
	private Response response;
	private String body;
	
	@Before
	public void setUp() {
		body = "";
	}
	
	@Test
	public void shouldBeASuccessfulResponseWhenStatusCodeWereOk() {
		response = new Response(HttpURLConnection.HTTP_OK, body);
		Assert.assertEquals(true, response.success());
	}
	
	@Test
	public void shouldBeASuccessfulResponseWhenStatusCodeWereCreated() {
		response = new Response(HttpURLConnection.HTTP_CREATED, body);
		Assert.assertEquals(true, response.success());
	}
	
	@Test
	public void shouldBeASuccessfulResponseWhenStatusCodeWereAccepted() {
		response = new Response(HttpURLConnection.HTTP_ACCEPTED, body);
		Assert.assertEquals(true, response.success());
	}
	
	@Test
	public void shouldBeASuccessfulResponseWhenStatusCodeWereNonAuthoritativeInformation() {
		response = new Response(HttpURLConnection.HTTP_NOT_AUTHORITATIVE, body);
		Assert.assertEquals(true, response.success());
	}
	
	@Test
	public void shouldBeASuccessfulResponseWhenStatusCodeWereNoContent() {
		response = new Response(HttpURLConnection.HTTP_NO_CONTENT, body);
		Assert.assertEquals(true, response.success());
	}
	
	@Test
	public void shouldBeASuccessfulResponseWhenStatusCodeWereNoResetContent() {
		response = new Response(HttpURLConnection.HTTP_RESET, body);
		Assert.assertEquals(true, response.success());
	}
	
	@Test
	public void shouldBeASuccessfulResponseWhenStatusCodeWerePartialContent() {
		response = new Response(HttpURLConnection.HTTP_PARTIAL, body);
		Assert.assertEquals(true, response.success());
	}
	
	@Test
	public void shouldBeAnUnsuccessfulResponseWhenStatusCodeWereBadRequest() {
		response = new Response(HttpURLConnection.HTTP_BAD_REQUEST, body);
		Assert.assertEquals(false, response.success());
	}
}
