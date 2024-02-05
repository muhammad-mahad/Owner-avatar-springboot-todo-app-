package com.spring.project.crudapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
	public static List<Todo> todos = new ArrayList<Todo>();
	public static int TODO_ID = 0;
	
	static {
		todos.add(new Todo(++TODO_ID, "Malik", "Learn AWS", 
							LocalDate.now().plusYears(1), false ));
		todos.add(new Todo(++TODO_ID, "Malik", "Learn DevOps", 
				LocalDate.now().plusYears(2), false ));
		todos.add(new Todo(++TODO_ID, "Malik", "Learn Full Stack Development", 
				LocalDate.now().plusYears(3), false ));
	}
	
	public List<Todo> findByUsername(String username){
		return todos;
	}
	
	public void addTodo(String username, String description, LocalDate localDate, boolean done) {
		todos.add(new Todo(++TODO_ID, username, description, 
				localDate, done ));
	}
	
	public void deleteById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.id() == id;
		todos.removeIf(predicate);
	}

	public Todo findById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.id() == id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}
	
	public void updateTodo(@Valid Todo todo) {
		deleteById(todo.id());
		todos.add(todo);
	}
}
