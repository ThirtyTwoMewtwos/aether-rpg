package com.aether.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import junit.framework.AssertionFailedError;

import org.junit.Before;
import org.junit.Test;


public class TestAssertCollection {
	
	private Collection<String> collection;

	@Before
	public void setUp() {
		collection = Arrays.asList("first", "second");
	}
	
	@Test
	public void test_Collection_with_items() throws Exception {
		AssertCollection.assertContains("first", collection);
		AssertCollection.assertContains("second", collection);
	}
	
	@Test
	public void test_Fails_when_item_not_contained() throws Exception {
		AssertThrows.assertThrows(AssertionFailedError.class, new CodeBlock() {
			@Override
			public void execute() throws Exception {
				AssertCollection.assertContains("third", collection);
			}
		});
	}
}
