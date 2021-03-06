package br.com.tealdi.httpclient.wrapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Iterator;

import br.com.tealdi.httpclient.Header;

public class HttpConnectionWrapper implements ConnectionWrapper {

	private HttpURLConnection connection;
	private String charsetEncoding;
	
	public HttpConnectionWrapper() {
		
	}
	
	@Override
	public void setRequestMethod(String verb) throws ProtocolException {
		connection.setRequestMethod(verb);
	}
	
	@Override
	public void setRequestHeader(Header header) {
		Iterator<String> headerIterator = header.getHeaders().keySet().iterator();
		
		while(headerIterator.hasNext()) {
			String headerKey = headerIterator.next();
			String headerValue = header.get(headerKey);
			
			connection.setRequestProperty(headerKey, headerValue);
		}
	}
	
	@Override
	public void setRequestBody(String body) throws IOException {
		OutputStreamWriter bodyStreamWriter = new OutputStreamWriter(connection.getOutputStream(), charsetEncoding);
		bodyStreamWriter.write(body);
		bodyStreamWriter.close();
	}
	
	@Override
	public void execute() throws IOException {
		connection.connect();
	}
	
	@Override
	public int getResponseStatusCode() throws IOException {
		return connection.getResponseCode();
	}
	
	@Override
	public Header getResponseHeader() throws IOException {
		Iterator<String> iterator = connection.getHeaderFields().keySet().iterator();
		
		Header header = new Header();
		
		while(iterator.hasNext()) {
			String headerKey = iterator.next();
			String headerValue = connection.getHeaderField(headerKey);
			header.add(headerKey, headerValue);
		}
		
		return header;
	}
	
	@Override
	public String getResponseBody() throws IOException {
		StringBuilder builderForBody = new StringBuilder();
		
		InputStream output;
		try {
			output  = connection.getInputStream();
		} catch(IOException exception) {
			output = connection.getErrorStream();
		}
		
		InputStreamReader streamReader = new InputStreamReader(output, charsetEncoding);

		int currentReadByte;
		
		while((currentReadByte = streamReader.read()) > -1) {
			builderForBody.append((char) currentReadByte);
		}
		
		output.close();
		streamReader.close();
		
		return builderForBody.toString();
	}

	@Override
	public void disconnect() {
		connection.disconnect();
	}

	@Override
	public void setUri(String uri) throws IOException {
		connection = (HttpURLConnection) new URL(uri).openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
	}

	@Override
	public void setCharsetEncoding(String charsetEncoding) {
		this.charsetEncoding = charsetEncoding;
	}
}
