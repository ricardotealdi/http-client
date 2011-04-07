package br.com.tealdi.httpclient;

public interface ServiceForHttpVerb {

	public abstract boolean requestBodyIsAllowedFor(String verb);
}
