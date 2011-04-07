package br.com.tealdi.httpclient;

import java.io.IOException;
import java.net.MalformedURLException;

public interface IHttpClient {

	public abstract Response doGet(Request request) throws MalformedURLException, IOException;
}
