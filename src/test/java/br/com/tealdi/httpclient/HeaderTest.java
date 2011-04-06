package br.com.tealdi.httpclient;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class HeaderTest {

	private Header header;
	
	@Before
	public void setUp() {
		header = new Header();
	}
	
	@Test 
	public void shouldBeAnEmptyHeaderIfItWereNotAdded() {
		Assert.assertEquals(null, header.get("Content-Type"));
	}
	
	@Test
	public void shouldAddAHeader() {
		header.add("Content-Type", "text/plain");
		Assert.assertEquals("text/plain", header.get("Content-Type"));
	}
}
