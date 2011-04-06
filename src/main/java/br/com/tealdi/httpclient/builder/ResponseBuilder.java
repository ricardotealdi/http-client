package br.com.tealdi.httpclient.builder;

import br.com.tealdi.httpclient.Response;

public class ResponseBuilder implements IResponseBuilder {

	@Override
	public Response buildWith(int httpStatusCode) {
		return new Response(httpStatusCode);
	}
	
}
