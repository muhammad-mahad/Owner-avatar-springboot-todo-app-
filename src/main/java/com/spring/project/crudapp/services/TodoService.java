package com.spring.project.crudapp.services;

import static com.spring.project.crudapp.CrudApplication.USER_NAME;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;
import com.spring.project.crudapp.models.Todo;
import com.spring.project.crudapp.repositories.TodoRepository;

@Service
public class TodoService {
	private TodoRepository todoRepository;

	public TodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	public List<Todo> getAll(String username) {
		List<Todo> todos = todoRepository.findByUsername(username);
		todos.sort(new Comparator<Todo>() {
			@Override
			public int compare(Todo todo1, Todo todo2) {
				return todo1.getId() - todo2.getId();
			}
		});
		return todos;
	}

	public Todo newTodo() {
		return new Todo("", LocalDate.now());
	}

	public void addTodo(Todo newTodo) {
		newTodo.setUsername(USER_NAME);
		todoRepository.save(newTodo);
	}

	public void deleteTodo(int id) {
		todoRepository.deleteById(id);
	}

	public Todo getTodo(int id) {
		return todoRepository.findById(id).orElse(null);
	}

	public void updateTodo(int id, Todo upateTodo) {
		upateTodo.setId(id);
		upateTodo.setUsername(USER_NAME);
		todoRepository.save(upateTodo);
	}
}
