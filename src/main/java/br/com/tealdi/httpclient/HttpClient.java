package br.com.tealdi.httpclient;

import br.com.tealdi.httpclient.wrapper.IHttpConnectorWrapper;

public class HttpClient implements IHttpClient {

	private final IHttpConnectorWrapper httpConnectorWrapperMocked;

	public HttpClient(IHttpConnectorWrapper httpConnectorWrapperMocked) {
		this.httpConnectorWrapperMocked = httpConnectorWrapperMocked;
	}

	@Override
	public Response doGet(Request request) {
		return httpConnectorWrapperMocked.connectTo(request, HttpVerb.GET);
	}

}
