package br.com.tealdi.httpclient;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.com.tealdi.httpclient.service.ConnectionService;

public class HttpClientTest {

	private ConnectionService httpConnectorWrapperMocked;
	private HttpClient httpClient;
	private Request requestToBeSent;
	private Response expectedResponse;

	@Before
	public void setUp() throws MalformedURLException, IOException {
		httpConnectorWrapperMocked = mock(ConnectionService.class);
		httpClient = new HttpClient(httpConnectorWrapperMocked);
		requestToBeSent = new Request("uri");
		expectedResponse = new Response(HttpURLConnection.HTTP_OK, "body", new Header());
		when(httpConnectorWrapperMocked.sendTo(isA(Request.class), anyString()))
			.thenReturn(expectedResponse);
	}
	
	@Test
	public void shouldRetrieveAResponseWhenDoingAGet() throws MalformedURLException, IOException {
		Assert.assertEquals(expectedResponse, httpClient.doGet(requestToBeSent));
	}
	
	@Test
	public void shouldUseTheWrapperWhenDoingAGet() throws MalformedURLException, IOException {
		httpClient.doGet(requestToBeSent);
		verify(httpConnectorWrapperMocked, times(1)).sendTo(requestToBeSent, HttpVerb.GET);
	}
	
	@Test
	public void shouldRetrieveAResponseWhenDoingAPost() throws MalformedURLException, IOException {
		Assert.assertEquals(expectedResponse, httpClient.doPost(requestToBeSent));
	}
	
	@Test
	public void shouldUseTheWrapperWhenDoingAPost() throws MalformedURLException, IOException {
		httpClient.doPost(requestToBeSent);
		verify(httpConnectorWrapperMocked, times(1)).sendTo(requestToBeSent, HttpVerb.POST);
	}
	
	@Test
	public void shouldRetrieveAResponseWhenDoingAPut() throws MalformedURLException, IOException {
		Assert.assertEquals(expectedResponse, httpClient.doPut(requestToBeSent));
	}
	
	@Test
	public void shouldUseTheWrapperWhenDoingAPut() throws MalformedURLException, IOException {
		httpClient.doPut(requestToBeSent);
		verify(httpConnectorWrapperMocked, times(1)).sendTo(requestToBeSent, HttpVerb.PUT);
	}
	
	@Test
	public void shouldRetrieveAResponseWhenDoingADelete() throws MalformedURLException, IOException {
		Assert.assertEquals(expectedResponse, httpClient.doDelete(requestToBeSent));
	}
	
	@Test
	public void shouldUseTheWrapperWhenDoingADelete() throws MalformedURLException, IOException {
		httpClient.doDelete(requestToBeSent);
		verify(httpConnectorWrapperMocked, times(1)).sendTo(requestToBeSent, HttpVerb.DELETE);
	}
}
