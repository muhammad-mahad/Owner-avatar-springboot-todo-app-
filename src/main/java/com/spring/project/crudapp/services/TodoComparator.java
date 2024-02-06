package com.spring.project.crudapp.services;

import com.spring.project.crudapp.models.Todo;

public class TodoComparator implements java.util.Comparator<Todo> {
	@Override
	public int compare(Todo todo1, Todo todo2) {
		return todo1.getId() - todo2.getId();
	}
}
