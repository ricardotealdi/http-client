package br.com.tealdi.httpclient.service;

import java.io.IOException;
import java.net.MalformedURLException;

import br.com.tealdi.httpclient.Request;
import br.com.tealdi.httpclient.Response;
import br.com.tealdi.httpclient.ServiceForHttpVerb;
import br.com.tealdi.httpclient.builder.ABuilderForResponse;
import br.com.tealdi.httpclient.wrapper.ConnectionWrapper;

public class HttpConnectionService implements ConnectionService {

	private final ABuilderForResponse builder;
	private final ServiceForHttpVerb verbService;
	private final ConnectionWrapper wrapper;
	
	public HttpConnectionService(
			ConnectionWrapper wrapper, 
			ABuilderForResponse builder,
			ServiceForHttpVerb verbService) {
				this.wrapper = wrapper;
				this.builder = builder;
				this.verbService = verbService;
	}

	@Override
	public Response sendTo(Request request, String httpVerb) throws MalformedURLException, IOException {
		wrapper.setUri(request.getUri());
		wrapper.setRequestMethod(httpVerb);
		wrapper.setRequestHeader(request.getHeader());
		
		if(verbService.requestBodyIsAllowedFor(httpVerb)) {
			wrapper.setRequestBody(request.getBody());
		}
		
		wrapper.execute();
		
		Response response = builder
				.buildWith(
						wrapper.getResponseStatusCode(), 
						wrapper.getResponseBody(),
						wrapper.getResponseHeader());
		
		wrapper.disconnect();
		
		return response;
	}
}
