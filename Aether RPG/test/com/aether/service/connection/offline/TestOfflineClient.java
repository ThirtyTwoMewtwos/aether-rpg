package com.aether.service.connection.offline;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.aether.service.connection.Client;
import com.aether.service.connection.offline.OfflineClient;
import com.aether.test.AssertThrows;
import com.aether.test.CodeBlock;


public class TestOfflineClient {
	private OfflineClient client;

	@Before 
	public void setUp() {
		client = new OfflineClient();
	}
	
	@Test
	public void testAlwaysLoginSupported() throws Exception {
		assertTrue(client.login(null, null));
	}
	
	@Test
	public void testNoErrorShouldOccur() throws Exception {
		AssertThrows.assertThrows(NotImplementedException.class, new CodeBlock() {
			@Override
			public void execute() throws Exception {
				client.getErrorMessage();
			}
		});
	}
}
