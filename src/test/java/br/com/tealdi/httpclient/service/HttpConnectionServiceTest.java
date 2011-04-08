package br.com.tealdi.httpclient.service;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.tealdi.httpclient.Header;
import br.com.tealdi.httpclient.Request;
import br.com.tealdi.httpclient.Response;
import br.com.tealdi.httpclient.ServiceForHttpVerb;
import br.com.tealdi.httpclient.builder.ABuilderForResponse;
import br.com.tealdi.httpclient.wrapper.ConnectionWrapper;

public class HttpConnectionServiceTest {
	
	private ConnectionWrapper wrapper;
	private ABuilderForResponse builder;
	private HttpConnectionService service;
	private ServiceForHttpVerb verbService;
	private String uri;
	private String body;
	private Header header;
	private Request request;
	private String verb;
	private Header responseHeader;

	@Before
	public void setUp() throws IOException {
		wrapper = mock(ConnectionWrapper.class);
		builder = mock(ABuilderForResponse.class);
		verbService = mock(ServiceForHttpVerb.class);
		
		responseHeader = new Header();
		
		when(wrapper.getResponseHeader())
			.thenReturn(responseHeader);
		
		service = new HttpConnectionService(wrapper, builder, verbService);
		
		uri = "uri";
		body = "body";
		header = new Header();
		request = new Request(uri, body, header);
		
		verb = "any-verb";
	}
	
	@Test
	public void shouldUseTheWrapperToSendRequestWhenDoingARequest() throws MalformedURLException, IOException {
		service.sendTo(request, verb);
		
		verify(wrapper, times(1)).setUri(uri);
		verify(wrapper, times(1)).setRequestMethod(verb);
		verify(wrapper, times(1)).setRequestHeader(header);
		verify(wrapper, times(0)).setRequestBody(body);
		verify(wrapper, times(1)).execute();
		verify(wrapper, times(1)).getResponseStatusCode();
		verify(wrapper, times(1)).getResponseHeader();
		verify(wrapper, times(1)).getResponseBody();
		verify(wrapper, times(1)).disconnect();
	}
	
	@Test
	public void shouldSendTheRequestBodyWhenItsAllowed() throws MalformedURLException, IOException {
		when(verbService.requestBodyIsAllowedFor(anyString()))
			.thenReturn(true);
		
		service.sendTo(request, verb);
		
		verify(wrapper, times(1)).setRequestBody(body);
	}
	
	@Test
	public void shouldNotSendTheRequestBodyWhenItsNotAllowed() throws MalformedURLException, IOException {
		when(verbService.requestBodyIsAllowedFor(anyString()))
			.thenReturn(false);
		
		service.sendTo(request, verb);
		
		verify(wrapper, times(0)).setRequestBody(body);
	}
	
	@Test
	public void shouldUseTheBuilder() throws MalformedURLException, IOException {
		
		int statusCode = 200;
		String responseBody = "response-body";
		Header responseHeader = new Header();
		
		when(wrapper.getResponseStatusCode()).thenReturn(statusCode);
		when(wrapper.getResponseBody()).thenReturn(responseBody);
		when(wrapper.getResponseHeader()).thenReturn(responseHeader);
		
		service.sendTo(request, verb);
		
		verify(builder, times(1)).buildWith(statusCode, responseBody, responseHeader);
	}
	
	@Test
	public void shouldUseTheVerbService() throws MalformedURLException, IOException {
		service.sendTo(request, verb);
		
		verify(verbService, times(1)).requestBodyIsAllowedFor(verb);
	}

	@Test
	public void shouldRetrieveTheResponse() throws MalformedURLException, IOException {
		Response expectedResponse = new Response(200, "response-body", responseHeader);
		
		when(builder.buildWith(anyInt(), anyString(), isA(Header.class)))
			.thenReturn(expectedResponse);
		
		Response retrievedResponse = service.sendTo(request, verb);
		
		Assert.assertEquals(expectedResponse, retrievedResponse);
	}
}
