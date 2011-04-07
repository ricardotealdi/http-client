package br.com.tealdi.httpclient;

import java.util.HashSet;
import java.util.Set;

public class HttpVerbService implements ServiceForHttpVerb {

private static Set<String> verbsWithRequestBodyAllowed;
	
	public boolean hasRequestBodyAllowedFor(String verb) {
		return getVerbsWithRequestBodyAllowed().contains(verb);
	}
	
	private Set<String> getVerbsWithRequestBodyAllowed() {
		if(verbsWithRequestBodyAllowed == null) {
			verbsWithRequestBodyAllowed = new HashSet<String>();
			verbsWithRequestBodyAllowed.add(HttpVerb.PUT);
			verbsWithRequestBodyAllowed.add(HttpVerb.POST);
		}
		return verbsWithRequestBodyAllowed;
	}
	
	@Override
	public boolean requestBodyIsAllowedFor(String verb) {
		return getVerbsWithRequestBodyAllowed().contains(verb);
	}

}
