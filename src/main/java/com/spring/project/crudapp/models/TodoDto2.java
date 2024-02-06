package com.spring.project.crudapp.models;

import java.time.LocalDate;

public class TodoDto2 extends TodoDto1 {
	protected boolean done;

	public TodoDto2() {
	}

	public TodoDto2(String description, LocalDate localDate, boolean done) {
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
