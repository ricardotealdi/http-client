package br.com.tealdi.httpclient.service;

import java.io.IOException;
import java.net.MalformedURLException;

import br.com.tealdi.httpclient.Request;
import br.com.tealdi.httpclient.Response;

public interface ConnectionService {
	public abstract Response sendTo(Request request, String httpVerb) throws MalformedURLException, IOException;
}
