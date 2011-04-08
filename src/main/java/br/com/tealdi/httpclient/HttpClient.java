package br.com.tealdi.httpclient;

import java.io.IOException;
import java.net.MalformedURLException;

import br.com.tealdi.httpclient.builder.ResponseBuilder;
import br.com.tealdi.httpclient.service.ConnectionService;
import br.com.tealdi.httpclient.service.HttpConnectionService;
import br.com.tealdi.httpclient.wrapper.HttpConnectionWrapper;

public class HttpClient implements RequestClient {

	private final ConnectionService connector;

	public HttpClient() {
		this(new HttpConnectionService(
				new HttpConnectionWrapper(),
				new ResponseBuilder(), 
				new HttpVerbService()));
	}
	
	public HttpClient(ConnectionService connector) {
		this.connector = connector;
	}

	@Override
	public Response doGet(Request request) throws MalformedURLException, IOException {
		return connector.sendTo(request, HttpVerb.GET);
	}

	@Override
	public Response doPost(Request request) throws MalformedURLException,
			IOException {
		return connector.sendTo(request, HttpVerb.POST);
	}

	@Override
	public Response doDelete(Request request) throws MalformedURLException,
			IOException {
		return connector.sendTo(request, HttpVerb.DELETE);
	}
	
	@Override
	public Response doPut(Request request) throws MalformedURLException,
	IOException {
		return connector.sendTo(request, HttpVerb.PUT);
	}

}
