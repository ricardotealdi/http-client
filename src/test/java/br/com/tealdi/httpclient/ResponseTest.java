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
		response = new Response(HttpURLConnection.HTTP_OK);
		Assert.assertEquals(true, response.success());
	}
	
	@Test
	public void shouldBeASuccessfulResponseWhenStatusCodeWereCreated() {
		response = new Response(HttpURLConnection.HTTP_CREATED);
		Assert.assertEquals(true, response.success());
	}
	
	@Test
	public void shouldBeASuccessfulResponseWhenStatusCodeWereAccepted() {
		response = new Response(HttpURLConnection.HTTP_ACCEPTED);
		Assert.assertEquals(true, response.success());
	}
	
	@Test
	public void shouldBeASuccessfulResponseWhenStatusCodeWereNonAuthoritativeInformation() {
		response = new Response(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
		Assert.assertEquals(true, response.success());
	}
	
	@Test
	public void shouldBeASuccessfulResponseWhenStatusCodeWereNoContent() {
		response = new Response(HttpURLConnection.HTTP_NO_CONTENT);
		Assert.assertEquals(true, response.success());
	}
	
	@Test
	public void shouldBeASuccessfulResponseWhenStatusCodeWereNoResetContent() {
		response = new Response(HttpURLConnection.HTTP_RESET);
		Assert.assertEquals(true, response.success());
	}
	
	@Test
	public void shouldBeASuccessfulResponseWhenStatusCodeWerePartialContent() {
		response = new Response(HttpURLConnection.HTTP_PARTIAL);
		Assert.assertEquals(true, response.success());
	}
	
	@Test
	public void shouldBeAnUnsuccessfulResponseWhenStatusCodeWereBadRequest() {
		response = new Response(HttpURLConnection.HTTP_BAD_REQUEST);
		Assert.assertEquals(false, response.success());
	}
}
