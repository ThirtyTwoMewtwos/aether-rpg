package com.aether.test;

import java.util.Collection;

import junit.framework.AssertionFailedError;

public class AssertCollection {
	public static void assertContains(Object contained, Collection<?> collection) {
		if (!collection.contains(contained)) {
			throw new AssertionFailedError("The object [" + contained + "] was not in the collection [" + collection + "].");
		}
	}
}
