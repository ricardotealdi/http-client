package br.com.tealdi.httpclient;

import org.junit.Assert;
import org.junit.Test;

public class RequestTest {

	@Test
	public void shouldCreateARequest() {
		Header header = new Header();
		header.add("Content-Type", "text/plain");
		
		String body = "body";
		String uri = "my-uri";
		
		String charsetEncoding = "ISO-8859-1";
		Request request = new Request(uri, body, header, charsetEncoding);
		
		Assert.assertEquals(uri, request.getUri()); 
		Assert.assertEquals(body, request.getBody()); 
		Assert.assertEquals(header, request.getHeader()); 
		Assert.assertEquals(charsetEncoding, request.getCharsetEncoding()); 
	}
	
	@Test
	public void shouldCreateARequestAsUtf8WhenPassAnEmptyStringAsCharsetEncoding() {
		
		Assert.assertEquals("UTF-8", new Request("", "", null, "").getCharsetEncoding()); 
	}
	
	@Test
	public void shouldCreateARequestAsUtf8WhenPassANullStringAsCharsetEncoding() {
		
		Assert.assertEquals("UTF-8", new Request("", "", null, null).getCharsetEncoding()); 
	}
}
