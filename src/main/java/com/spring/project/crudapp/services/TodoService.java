package com.spring.project.crudapp.services;

import static com.spring.project.crudapp.CrudApplication.USER_NAME;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;
import com.spring.project.crudapp.models.Todo;
import com.spring.project.crudapp.models.TodoDto1;
import com.spring.project.crudapp.models.TodoDto2;
import com.spring.project.crudapp.repositories.TodoRepository;

@Service
public class TodoService {
	private TodoRepository todoRepository;

	public TodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	public List<Todo> getAll(String username) {
		List<Todo> todos = todoRepository.findByUsername(username);
		Collections.sort(todos, new TodoComparator());
		return todos;
	}

	public TodoDto1 newTodo() {
		return new TodoDto1(new String(), LocalDate.now());
	}

	public void addTodo(TodoDto1 todo) {
		Todo newTodo = new Todo(USER_NAME, todo.getDescription(), todo.getLocalDate(), false);
		todoRepository.save(newTodo);
	}

	public void deleteTodo(int id) {
		todoRepository.deleteById(id);
	}

	public Todo getTodo(int id) {
		return todoRepository.findById(id).orElse(null);
	}

	public void updateTodo(int id, TodoDto2 todo) {
		Todo upateTodo = new Todo(id, USER_NAME, todo.getDescription(), todo.getLocalDate(), todo.isDone());
		todoRepository.save(upateTodo);
	}
}
