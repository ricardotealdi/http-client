package br.com.tealdi.httpclient.builder;

import br.com.tealdi.httpclient.Header;
import br.com.tealdi.httpclient.Response;

public class ResponseBuilder implements ABuilderForResponse {

	@Override
	public Response buildWith(int httpStatusCode, String body, Header header) {
		return new Response(httpStatusCode, body, header);
	}
	
}
