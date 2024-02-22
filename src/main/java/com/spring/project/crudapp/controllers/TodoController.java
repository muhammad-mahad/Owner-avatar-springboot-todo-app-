package com.spring.project.crudapp.controllers;

import static com.spring.project.crudapp.CrudApplication.USER_NAME;
import static com.spring.project.crudapp.CrudeAppData.REDIRECT;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.project.crudapp.CrudeAppData;
import com.spring.project.crudapp.models.Todo;
import com.spring.project.crudapp.services.TodoService;

@Controller
public class TodoController {
	private TodoService todoService;
	public static final String REDIRECT_TO_GET_ALL = REDIRECT + CrudeAppData.GET_ALL;

	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	@RequestMapping
	public String mainPage() {
		return REDIRECT_TO_GET_ALL;
	}

	@RequestMapping(CrudeAppData.GET_ALL)
	public String listAlltodos(ModelMap model) {
		List<Todo> todos = todoService.getAll(USER_NAME);
		model.addAttribute(CrudeAppData.TODO, todos);
		return CrudeAppData.GET_ALL_HTML;
	}
	
	private void addNewTodoModelAttribute(ModelMap model, Todo todo) {
		if (todo == null)
			todo = todoService.newTodo();
		model.addAttribute(CrudeAppData.TODO, todo);
		model.addAttribute(CrudeAppData.PAGE_TITLE, CrudeAppData.ADD_PAGE);
	}

	@RequestMapping(value = CrudeAppData.ADD_TODO, method = RequestMethod.GET)
	public String newTodo(ModelMap model) {
		addNewTodoModelAttribute(model, null);
		return CrudeAppData.TODO;
	}

	@RequestMapping(value = CrudeAppData.ADD_TODO, method = RequestMethod.POST)
	public String postNewTodo(@Validated @ModelAttribute(CrudeAppData.TODO) Todo todo, BindingResult bindingResult,
			ModelMap model) {
		if (bindingResult.hasErrors()) {
			addNewTodoModelAttribute(model, todo);
			return CrudeAppData.TODO;
		}
		todoService.addTodo(todo);
		return REDIRECT_TO_GET_ALL;
	}

	@RequestMapping(CrudeAppData.DELETE_TODO)
	public String deleteTodo(@RequestParam int id) {
		todoService.deleteTodo(id);
		return REDIRECT_TO_GET_ALL;
	}

	private void addUpdateTodoAttribute(ModelMap model, Todo todoDto, int id) {
		if (todoDto == null) {
			Todo todo = todoService.getTodo(id);
			todoDto = new Todo(todo.getDescription(), todo.getLocalDate(), todo.isDone());
		}
		model.addAttribute(CrudeAppData.TODO, todoDto);
		model.addAttribute(CrudeAppData.PAGE_TITLE, CrudeAppData.UPDATE_PAGE);
	}

	@RequestMapping(value = CrudeAppData.UPDATE_TOOD, method = RequestMethod.GET)
	public String updateTodo(@RequestParam int id, ModelMap model) {
		addUpdateTodoAttribute(model, null, id);
		return CrudeAppData.TODO;
	}

	@RequestMapping(value = CrudeAppData.UPDATE_TOOD, method = RequestMethod.POST)
	public String postUpdateTodo(@RequestParam int id, @Validated @ModelAttribute(CrudeAppData.TODO) Todo todo,
			BindingResult bindingResult, ModelMap model) {
		if (bindingResult.hasErrors()) {
			addUpdateTodoAttribute(model, todo, id);
			return CrudeAppData.TODO;
		}
		todoService.updateTodo(id, todo);
		return REDIRECT_TO_GET_ALL;
	}
}
