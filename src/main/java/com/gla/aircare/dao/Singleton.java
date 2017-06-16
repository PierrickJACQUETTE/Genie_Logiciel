package com.gla.aircare.dao;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public final class Singleton {

	private static volatile Singleton instance = null;
	private Client client;

	@SuppressWarnings({ "resource" })
	private Singleton() {
		try {
			client = new PreBuiltTransportClient(Settings.EMPTY)
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public final static Singleton getInstance() {
		if (Singleton.instance == null) {
			synchronized (Singleton.class) {
				if (Singleton.instance == null) {
					Singleton.instance = new Singleton();
				}
			}
		}
		return Singleton.instance;
	}

	public Client getClient() {
		return client;
	}

}