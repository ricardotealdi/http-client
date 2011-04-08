package br.com.tealdi.httpclient.wrapper;

import java.io.IOException;
import java.net.MalformedURLException;

import br.com.tealdi.httpclient.Request;
import br.com.tealdi.httpclient.Response;

public interface ConnectorWrapper {
	public abstract Response sendTo(Request request, String httpVerb) throws MalformedURLException, IOException;
}
