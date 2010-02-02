package com.aether.service.connection.offline;

import org.gap.jseed.injection.annotation.Singleton;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.aether.service.connection.Client;

@Singleton
public class OfflineClient implements Client {

	@Override
	public String getErrorMessage() {
		throw new NotImplementedException();
	}

	@Override
	public boolean login(String string, String string2) {
		return true;
	}

}
