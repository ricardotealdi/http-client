package br.com.tealdi.httpclient;

import java.net.HttpURLConnection;
import java.util.HashSet;
import java.util.Set;

public class StatusCode {
	private static Set<Integer> successfulStatusCodes;

	public static void populateSuccessfulStatusCodes() {
		successfulStatusCodes = new HashSet<Integer>(7);
		successfulStatusCodes.add(HttpURLConnection.HTTP_OK);
		successfulStatusCodes.add(HttpURLConnection.HTTP_CREATED);
		successfulStatusCodes.add(HttpURLConnection.HTTP_ACCEPTED);
		successfulStatusCodes.add(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
		successfulStatusCodes.add(HttpURLConnection.HTTP_NO_CONTENT);
		successfulStatusCodes.add(HttpURLConnection.HTTP_RESET);
		successfulStatusCodes.add(HttpURLConnection.HTTP_PARTIAL);;
	}

	public static Set<Integer> getSuccessfulStatusCodes() {
		if(successfulStatusCodes == null) {
			populateSuccessfulStatusCodes();
		}
		
		return successfulStatusCodes;
	}
}
