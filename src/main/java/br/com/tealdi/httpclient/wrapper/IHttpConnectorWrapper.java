package br.com.tealdi.httpclient.wrapper;

import java.io.IOException;
import java.net.MalformedURLException;

import br.com.tealdi.httpclient.Request;
import br.com.tealdi.httpclient.Response;

public interface IHttpConnectorWrapper {
	public abstract Response connectTo(Request request, String httpVerb) throws MalformedURLException, IOException;
}
