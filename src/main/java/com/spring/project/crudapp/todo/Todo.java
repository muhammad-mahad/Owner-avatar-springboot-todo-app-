package com.spring.project.crudapp.todo;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;

public record Todo(int id, String username, @Size(min = 10, message = "Enter atleast 10 characters") String description,
		@FutureOrPresent(message = "Enter future or valid date") LocalDate localDate, boolean done) {
}

record TodoDTO(@Size(min = 10, message = "Enter atleast 10 characters") String description,
		@FutureOrPresent(message = "Enter future or valid date") LocalDate localDate) {
}

record TodoUpdateDTO(@Size(min = 10, message = "Enter atleast 10 characters") String description,
		@FutureOrPresent(message = "Enter future or valid date") LocalDate localDate, boolean done) {
}

class TodoComparator implements java.util.Comparator<Todo> {
	@Override
	public int compare(Todo todo1, Todo todo2) {
		return todo1.id() - todo2.id();
	}
}
