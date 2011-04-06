package br.com.tealdi.httpclient.wrapper;

import br.com.tealdi.httpclient.Request;
import br.com.tealdi.httpclient.Response;

public interface IHttpConnectorWrapper {
	public abstract Response connectTo(Request request, String httpVerb);
}
