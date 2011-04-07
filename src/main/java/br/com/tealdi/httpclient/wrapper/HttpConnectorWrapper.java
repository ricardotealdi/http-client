package br.com.tealdi.httpclient.wrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Iterator;

import br.com.tealdi.httpclient.Header;
import br.com.tealdi.httpclient.Request;
import br.com.tealdi.httpclient.Response;
import br.com.tealdi.httpclient.builder.IResponseBuilder;

public class HttpConnectorWrapper implements IHttpConnectorWrapper {

	private final IResponseBuilder builder;

	public HttpConnectorWrapper(IResponseBuilder builder) {
		this.builder = builder;
	}
	
	@Override
	public Response connectTo(Request request, String httpVerb) throws MalformedURLException, IOException {
		
		HttpURLConnection connection = 
			(HttpURLConnection) new URL(request.getUri()).openConnection();
		connection.setRequestMethod(httpVerb);
		
		setRequestHeader(connection, request.getHeader());
		Response response;
		
		try {
			connection.setDoOutput(true);
			setRequestBody(request.getBody(), connection.getOutputStream());
			
			response = readResponse(connection);
		} catch(UnknownHostException exception) {
			response = builder.buildWith(HttpURLConnection.HTTP_BAD_GATEWAY, "", new Header());
		} catch(IOException exception) {
			response = builder.buildWith(connection.getResponseCode(), "", new Header());
		}
		
		//readResponse(connection)
				
		return response;
	}

	private Response readResponse(HttpURLConnection connection) throws IOException {

		int statusCode = connection.getResponseCode();
		Header header = getHeader(connection);
		String body = getBody(connection);
		
		return builder.buildWith(statusCode, body, header);
	}
	
	private Header getHeader(HttpURLConnection connection) {
		Iterator<String> iterator = connection.getHeaderFields().keySet().iterator();
		
		Header header = new Header();
		
		while(iterator.hasNext()) {
			String headerKey = iterator.next();
			String headerValue = connection.getHeaderField(headerKey);
			header.add(headerKey, headerValue);
		}
		
		return header;
	}
	
	private String getBody(HttpURLConnection connection) throws IOException {
		
		StringBuffer buffer = new StringBuffer();
		String line = "";

		BufferedReader in = 
			new BufferedReader(new InputStreamReader(connection.getInputStream()));

		while ((line = in.readLine()) != null)
			buffer.append(line + "\r");

		in.close();
		
		return buffer.toString();
	}
	
	private void setRequestBody(String body, OutputStream outputStream)
			throws IOException {	
		OutputStreamWriter out = new OutputStreamWriter(outputStream);
		out.write(body);
		out.close();
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
