package br.com.tealdi.httpclient.wrapper;

import java.io.IOException;
import java.net.ProtocolException;

import br.com.tealdi.httpclient.Header;

public interface ConnectionWrapper {

	public abstract void setRequestMethod(String verb) throws ProtocolException;

	public abstract void setRequestHeader(Header header);

	public abstract void setRequestBody(String body) throws IOException;

	public abstract void execute() throws IOException;

	public abstract int getResponseStatusCode() throws IOException;

	public abstract Header getResponseHeader() throws IOException;

	public abstract String getResponseBody() throws IOException;

}