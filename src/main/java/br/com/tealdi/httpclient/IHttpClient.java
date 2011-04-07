package br.com.tealdi.httpclient;

import java.io.IOException;
import java.net.MalformedURLException;

public interface IHttpClient {

	public abstract Response doGet(Request request) throws MalformedURLException, IOException;
	public abstract Response doPost(Request request) throws MalformedURLException, IOException;
	public abstract Response doDelete(Request request) throws MalformedURLException, IOException;
	public abstract Response doPut(Request request) throws MalformedURLException, IOException;
}
