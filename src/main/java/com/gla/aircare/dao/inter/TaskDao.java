package com.gla.aircare.dao.inter;

import java.util.List;

import com.gla.aircare.dao.object.Task;

public interface TaskDao {

	public int addTask(Task task);

	public Task getTask(String idTask);

	public List<Task> getTasks();

	public int setTask(Task task);
}
