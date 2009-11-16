package com.aether.test;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;

public class AssertThrows {

	public static void assertThrows(Class<? extends Throwable> expected, CodeBlock codeBlock) {
		try {
			codeBlock.execute();
		} catch (Throwable thrown){
			if (!expected.equals(thrown.getClass())) {
				String message = String.format("Expected [%s], but was [%s].", expected.getName(), thrown.getClass().getName()); //$NON-NLS-1$
				throw new AssertionFailedError(message);
			}
			return;
		}
		Assert.fail(String.format("Expected [%s], but no exception was thrown.", expected.getName())); //$NON-NLS-1$
	}
}
