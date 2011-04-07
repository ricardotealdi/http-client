package br.com.tealdi.httpclient.wrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
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
		connection.setDoInput(true);
		connection.setDoOutput(true);
		
		setRequestHeader(connection, request.getHeader());
		Response response;
		
		try {
			setRequestBody(request.getBody(), connection.getOutputStream());
			
			response = readResponse(connection);
		} catch(UnknownHostException exception) {
			response = 
				builder
					.buildWith(
							HttpURLConnection.HTTP_BAD_GATEWAY, 
							getErrorBody(connection), 
							new Header());
		} catch(IOException exception) {
			response = 
				builder
					.buildWith(
							connection.getResponseCode(), 
							getErrorBody(connection), 
							new Header());
		}
		
		connection.disconnect();
				
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
	
	private String getBody(HttpURLConnection connection, boolean success) throws IOException {
		StringBuffer buffer = new StringBuffer();
		String lineRetrieved = "";

		InputStream inputStream;
		if(success) {
			inputStream = connection.getInputStream();
		} else {
			inputStream = connection.getErrorStream();
		}
		
		BufferedReader bufferedReader = 
			new BufferedReader(new InputStreamReader(inputStream));

		while ((lineRetrieved = bufferedReader.readLine()) != null)
			buffer.append(lineRetrieved + "\r");

		inputStream.close();
		bufferedReader.close();
		
		return lineRetrieved;
	}
	
	private String getErrorBody(HttpURLConnection connection) throws IOException {
		return getBody(connection, false);
	}
	
	private String getBody(HttpURLConnection connection) throws IOException {
		return getBody(connection, true);
	}
	
	private void setRequestBody(String body, OutputStream outputStream)
			throws IOException {	
		OutputStreamWriter outputStreamWritter = new OutputStreamWriter(outputStream);
		outputStreamWritter.write(body);
		outputStreamWritter.flush();
		outputStreamWritter.close();
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
