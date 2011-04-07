package br.com.tealdi.httpclient;

import java.io.IOException;
import java.net.MalformedURLException;

import br.com.tealdi.httpclient.builder.ResponseBuilder;
import br.com.tealdi.httpclient.wrapper.HttpConnectorWrapper;
import br.com.tealdi.httpclient.wrapper.ConnectorWrapper;

public class HttpClient implements RequestClient {

	private final ConnectorWrapper connector;

	public HttpClient() {
		this(new HttpConnectorWrapper(
				new ResponseBuilder(), 
				new HttpVerbService()));
	}
	
	public HttpClient(ConnectorWrapper connector) {
		this.connector = connector;
	}

	@Override
	public Response doGet(Request request) throws MalformedURLException, IOException {
		return connector.connectTo(request, HttpVerb.GET);
	}

	@Override
	public Response doPost(Request request) throws MalformedURLException,
			IOException {
		return connector.connectTo(request, HttpVerb.POST);
	}

	@Override
	public Response doDelete(Request request) throws MalformedURLException,
			IOException {
		return connector.connectTo(request, HttpVerb.DELETE);
	}
	
	@Override
	public Response doPut(Request request) throws MalformedURLException,
	IOException {
		return connector.connectTo(request, HttpVerb.PUT);
	}

}
