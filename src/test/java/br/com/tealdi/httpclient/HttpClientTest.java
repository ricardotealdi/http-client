package br.com.tealdi.httpclient;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.HttpURLConnection;

import junit.framework.Assert;

import org.junit.Test;

import br.com.tealdi.httpclient.wrapper.IHttpConnectorWrapper;

public class HttpClientTest {

	@Test
	public void ShouldDoAGet() {
		IHttpConnectorWrapper httpConnectorWrapperMocked = mock(IHttpConnectorWrapper.class);
		IHttpClient httpClient = new HttpClient(httpConnectorWrapperMocked);
		Request requestToBeSent = new Request("uri");
		Response expectedResponse = new Response(HttpURLConnection.HTTP_OK, "body", new Header());
		when(httpConnectorWrapperMocked.connectTo(isA(Request.class), anyString()))
				.thenReturn(expectedResponse);

		Assert.assertEquals(expectedResponse, httpClient.doGet(requestToBeSent));
	}
}
