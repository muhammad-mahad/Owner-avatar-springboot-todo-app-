package com.spring.project.crudapp.todo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;

@Entity
public class Todo {

	public Todo() {
	}

	public Todo(int id, String username, String description, LocalDate localDate, boolean done) {
		this.id = id;
		this.username = username;
		this.description = description;
		this.localDate = localDate;
		this.done = done;
	}
	
	public Todo(String username, String description, LocalDate localDate, boolean done) {
		this.id = 0;
		this.username = username;
		this.description = description;
		this.localDate = localDate;
		this.done = done;
	}

	@Id
	@GeneratedValue
	private int id;
	private String username;
	@Size(min = 10, message = "Enter atleast 10 characters")
	private String description;
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
	
	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", username=" + username + ", description=" + description + ", targetDate="
				+ localDate + ", done=" + done + "]";
	}

}

class TodoDTO {
	@Size(min = 10, message = "Enter atleast 10 characters")
	protected String description;
	@FutureOrPresent(message = "Enter future or valid date")
	protected LocalDate localDate;

	public TodoDTO() {
	}

	TodoDTO(String description, LocalDate localDate) {
		this.description = description;
		this.localDate = localDate;
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
}

class TodoUpdateDTO extends TodoDTO {
	protected boolean done;

	public TodoUpdateDTO() {
	}

	TodoUpdateDTO(String description, LocalDate localDate, boolean done) {
		super(description, localDate);
		this.done = done;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
}

class TodoComparator implements java.util.Comparator<Todo> {
	@Override
	public int compare(Todo todo1, Todo todo2) {
		return todo1.getId() - todo2.getId();
	}
}
