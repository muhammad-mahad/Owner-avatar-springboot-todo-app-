package com.spring.project.crudapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.project.crudapp.models.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
	public List<Todo> findByUsername(String username);
}
