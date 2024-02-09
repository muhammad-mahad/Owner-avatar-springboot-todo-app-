package com.spring.project.crudapp.services;

import java.util.List;

import com.spring.project.crudapp.models.Todo;

public class TodoCollectorsMap {

	public static final boolean isAnyMissed(List<Todo> todos) {
		return todos.stream().anyMatch(todo -> todo.isInPast() && !todo.isDone());
	}

	public static final boolean isAllCompleted(List<Todo> todos) {
		return todos.stream().allMatch(todo -> todo.isDone());
	}
}
