package br.com.tealdi.httpclient.builder;

import br.com.tealdi.httpclient.Response;

public interface IResponseBuilder {

	public abstract Response buildWith(int httpStatusCode, String body);
}
