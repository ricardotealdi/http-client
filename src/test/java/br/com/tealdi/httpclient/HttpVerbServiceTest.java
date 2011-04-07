package br.com.tealdi.httpclient;

import junit.framework.Assert;

import org.junit.Test;

public class HttpVerbServiceTest {

	@Test
	public void aPostRequestShouldBeAllowedToHaveARequestBody() {
		HttpVerbService service = new HttpVerbService();
		
		Assert.assertEquals(true, service.requestBodyIsAllowedFor(HttpVerb.POST));
	}
	
	@Test
	public void aPutRequestShouldBeAllowedToHaveARequestBody() {
		HttpVerbService service = new HttpVerbService();
		
		Assert.assertEquals(true, service.requestBodyIsAllowedFor(HttpVerb.PUT));
	}
	
	@Test
	public void aGetRequestShouldNotBeAllowedToHaveARequestBody() {
		HttpVerbService service = new HttpVerbService();
		
		Assert.assertEquals(false, service.requestBodyIsAllowedFor(HttpVerb.GET));
	}
	
	@Test
	public void aHeadRequestShouldNotBeAllowedToHaveARequestBody() {
		HttpVerbService service = new HttpVerbService();
		
		Assert.assertEquals(false, service.requestBodyIsAllowedFor(HttpVerb.HEAD));
	}
	
	@Test
	public void aOptionsRequestShouldNotBeAllowedToHaveARequestBody() {
		HttpVerbService service = new HttpVerbService();
		
		Assert.assertEquals(false, service.requestBodyIsAllowedFor(HttpVerb.OPTIONS));
	}
	
	@Test
	public void aTraceRequestShouldNotBeAllowedToHaveARequestBody() {
		HttpVerbService service = new HttpVerbService();
		
		Assert.assertEquals(false, service.requestBodyIsAllowedFor(HttpVerb.TRACE));
	}
	
	@Test
	public void aConnectRequestShouldNotBeAllowedToHaveARequestBody() {
		HttpVerbService service = new HttpVerbService();
		
		Assert.assertEquals(false, service.requestBodyIsAllowedFor(HttpVerb.CONNECT));
	}
}
