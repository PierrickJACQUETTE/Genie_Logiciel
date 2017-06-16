package com.gla.aircare.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.SearchHit;

import com.gla.aircare.dao.Dao;
import com.gla.aircare.dao.Singleton;
import com.gla.aircare.dao.inter.TaskDao;
import com.gla.aircare.dao.object.Task;

public class TaskDaoImpl implements TaskDao {

	private Client client;

	public TaskDaoImpl() {
		client = Singleton.getInstance().getClient();
	}

	
	public int addTask(Task task) {
		IndexResponse indexResponse = client.prepareIndex(Dao.NAMEBASE, Dao.NAMETABLETASK, task.getIdTask())
				.setSource(task.create()).get();
		return indexResponse.status().getStatus();
	}

	
	public Task getTask(String idTask) {
		Task task = new Task();
		GetResponse getResponse = client.prepareGet(Dao.NAMEBASE, Dao.NAMETABLETASK, idTask).execute().actionGet();
		return task.createObject(getResponse.getSourceAsString());
	}

	
	public List<Task> getTasks() {
		SearchResponse searchResponse = client.prepareSearch(Dao.NAMEBASE).setTypes(Dao.NAMETABLETASK)
				.setSize(Dao.MAXGET).get();
		List<Task> list = new ArrayList<Task>();
		Task task = new Task();
		for (SearchHit hit : searchResponse.getHits()) {
			list.add(task.createObject(hit.getSourceAsString()));
		}
		return list;
	}

	
	public int setTask(Task task) {
		UpdateResponse updateResponse = client.prepareUpdate(Dao.NAMEBASE, Dao.NAMETABLETASK, task.getIdTask())
				.setDoc(task.create()).get();
		return updateResponse.status().getStatus();
	}

}
