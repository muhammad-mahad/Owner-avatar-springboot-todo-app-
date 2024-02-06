package com.spring.project.crudapp.models;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Size;

public class TodoDto1 {
	@Size(min = 10, message = "Enter atleast 10 characters")
	protected String description;
	@FutureOrPresent(message = "Enter future or valid date")
	protected LocalDate localDate;

	public TodoDto1() {
	}

	public TodoDto1(String description, LocalDate localDate) {
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