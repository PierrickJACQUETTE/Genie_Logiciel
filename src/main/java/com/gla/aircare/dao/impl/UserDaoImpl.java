package com.gla.aircare.dao.impl;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;

import com.gla.aircare.dao.Dao;
import com.gla.aircare.dao.Singleton;
import com.gla.aircare.dao.inter.UserDao;
import com.gla.aircare.dao.object.User;

public class UserDaoImpl implements UserDao {

	private Client client;

	public UserDaoImpl() {
		client = Singleton.getInstance().getClient();
	}

	
	public User getUser(String idUser) {
		User user = new User();
		GetResponse getResponse = client.prepareGet(Dao.NAMEBASE, Dao.NAMETABLEUSER, idUser).execute().actionGet();
		return user.createObject(getResponse.getSourceAsString());
	}

	
	public int setUser(User user) {
		UpdateResponse updateResponse = client.prepareUpdate(Dao.NAMEBASE, Dao.NAMETABLEUSER, user.getIdUser())
				.setDoc(user.create()).get();
		return updateResponse.status().getStatus();
	}


	public int addUser(User user) {
		IndexResponse indexResponse = client.prepareIndex(Dao.NAMEBASE, Dao.NAMETABLEUSER, user.getIdUser())
				.setSource(user.create()).get();
		return indexResponse.status().getStatus();
	}

}
