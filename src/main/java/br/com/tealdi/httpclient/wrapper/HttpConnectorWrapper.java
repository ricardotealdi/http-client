package br.com.tealdi.httpclient.wrapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;

import br.com.tealdi.httpclient.Header;
import br.com.tealdi.httpclient.Request;
import br.com.tealdi.httpclient.Response;
import br.com.tealdi.httpclient.ServiceForHttpVerb;
import br.com.tealdi.httpclient.builder.Builder;

public class HttpConnectorWrapper implements ConnectorWrapper {

	private final Builder builder;
	private final ServiceForHttpVerb verbService;

	public HttpConnectorWrapper(Builder builder, ServiceForHttpVerb verbService) {
		this.builder = builder;
		this.verbService = verbService;
	}
	
	@Override
	public Response connectTo(Request request, String httpVerb) throws MalformedURLException, IOException {
		URL url = new URL(request.getUri());
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);
		urlConnection.setRequestMethod(httpVerb);
		
		setRequestHeader(urlConnection, request.getHeader());
		if(verbService.requestBodyIsAllowedFor(httpVerb)) {
			setRequestBody(urlConnection, request.getBody());
		}
		urlConnection.connect();
		
		return readResponse(urlConnection);
	}

	private Response readResponse(HttpURLConnection connection) throws IOException {

		int statusCode = connection.getResponseCode();
		Header header = getHeader(connection);
		String body = getBody(connection);
		
		connection.disconnect();
		
		return builder.buildWith(statusCode, body, header);
	}
	
	private Header getHeader(HttpURLConnection urlConnection) {
		Iterator<String> iterator = urlConnection.getHeaderFields().keySet().iterator();
		
		Header header = new Header();
		
		while(iterator.hasNext()) {
			String headerKey = iterator.next();
			String headerValue = urlConnection.getHeaderField(headerKey);
			header.add(headerKey, headerValue);
		}
		
		return header;
	}
	
	private String getBody(HttpURLConnection urlConnection) throws IOException {
		StringBuilder builderForBody = new StringBuilder();
		
		InputStream output;
		try {
			output  = urlConnection.getInputStream();
		} catch(IOException exception) {
			output = urlConnection.getErrorStream();
		}
		
		Scanner scanner = new Scanner(output);
		
		while(scanner.hasNextLine()) {
			builderForBody.append(scanner.nextLine());
			builderForBody.append("\r");
		}
		
		output.close();
		scanner.close();
		
		return builderForBody.toString();
	}
	
	private void setRequestBody(HttpURLConnection urlConnection, String body)
			throws IOException {
		PrintWriter bodyStream = new PrintWriter(urlConnection.getOutputStream());
		bodyStream.print(body);
		bodyStream.close();
	}

	private void setRequestHeader(HttpURLConnection connection, Header header) {
		Iterator<String> headerIterator = header.getHeaders().keySet().iterator();
		
		while(headerIterator.hasNext()) {
			String headerKey = headerIterator.next();
			String headerValue = header.get(headerKey);
			
			connection.setRequestProperty(headerKey, headerValue);
		}
	}
}
