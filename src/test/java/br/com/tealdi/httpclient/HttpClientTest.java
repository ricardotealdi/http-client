package br.com.tealdi.httpclient;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.net.HttpURLConnection;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.com.tealdi.httpclient.wrapper.IHttpConnectorWrapper;

public class HttpClientTest {

	private IHttpConnectorWrapper httpConnectorWrapperMocked;
	private HttpClient httpClient;
	private Request requestToBeSent;
	private Response expectedResponse;

	@Before
	public void setUp() {
		httpConnectorWrapperMocked = mock(IHttpConnectorWrapper.class);
		httpClient = new HttpClient(httpConnectorWrapperMocked);
		requestToBeSent = new Request("uri");
		expectedResponse = new Response(HttpURLConnection.HTTP_OK, "body", new Header());
		when(httpConnectorWrapperMocked.connectTo(isA(Request.class), anyString()))
			.thenReturn(expectedResponse);
	}
	
	@Test
	public void shouldRetrieveAResponseWhenDoingAGet() {
		Assert.assertEquals(expectedResponse, httpClient.doGet(requestToBeSent));
	}
	
	@Test
	public void shouldUseTheWrapperWhenDoingAGet() {
		httpClient.doGet(requestToBeSent);
		verify(httpConnectorWrapperMocked, times(1)).connectTo(requestToBeSent, HttpVerb.GET);
	}
}