package com.spring.project.crudapp.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Todo {

	public Todo() {
	}

	public Todo(String description, LocalDate localDate) {
		this(-1, "", description, localDate, false);
	}

	public Todo(String description, LocalDate localDate, boolean done) {
		this(-1, "", description, localDate, done);
	}

	public Todo(String username, String description, LocalDate localDate, boolean done) {
		this(-1, username, description, localDate, done);
	}

	public Todo(int id, String username, String description, LocalDate localDate, boolean done) {
		this.id = id;
		this.username = username;
		this.description = description;
		this.localDate = localDate;
		this.done = done;
	}

	@Id
	@GeneratedValue
	private int id;
	private String username;
	@Size(min = 10, message = "Description must be at least 10 characters long")
	private String description;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@FutureOrPresent(message = "Enter future or valid date")
	private LocalDate localDate;
	private boolean done;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getLocalDate() {
		return localDate;
	}

	public void setLocalDate(LocalDate targetDate) {
		this.localDate = targetDate;
	}

	public boolean isInPast() {
		return localDate.isBefore(LocalDate.now());
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public static final boolean isAnyMissed(List<Todo> todos) {
		return todos.stream().anyMatch(todo -> todo.isInPast() && !todo.isDone());
	}

	public static final boolean isAllCompleted(List<Todo> todos) {
		return todos.stream().allMatch(todo -> todo.isDone());
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", USER_NAME=" + username + ", description=" + description + ", targetDate="
				+ localDate + ", done=" + done + "]";
	}
}
