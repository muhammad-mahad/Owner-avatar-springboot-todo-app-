package com.spring.project.crudapp.controllers;

import static com.spring.project.crudapp.CrudApplication.USER_NAME;

import java.util.List;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.project.crudapp.CrudeAppData;
import com.spring.project.crudapp.models.Todo;
import com.spring.project.crudapp.models.TodoDto1;
import com.spring.project.crudapp.models.TodoDto2;
import com.spring.project.crudapp.services.TodoService;

@Controller
public class TodoController {
	private TodoService todoService;
	public static final String REDIRECT_TO_GET_ALL = CrudeAppData.REDIRECT + CrudeAppData.GET_ALL;

	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	@RequestMapping("/")
	public String mainPage() {
		return REDIRECT_TO_GET_ALL;
	}

	@RequestMapping(CrudeAppData.GET_ALL)
	public String listAlltodos(ModelMap model) {
		List<Todo> todos = todoService.getAll(USER_NAME);
		model.addAttribute(CrudeAppData.TODO, todos);
		return CrudeAppData.GET_ALL_HTML;
	}

	@RequestMapping(value = CrudeAppData.ADD_TODO, method = RequestMethod.GET)
	public String newTodo(ModelMap model) {
		TodoDto1 todo = todoService.newTodo();
		model.addAttribute(CrudeAppData.TODO, todo);
		model.addAttribute(CrudeAppData.PAGE_TITLE, CrudeAppData.ADD_PAGE);
		return CrudeAppData.TODO;
	}

	@RequestMapping(value = CrudeAppData.ADD_TODO, method = RequestMethod.POST)
	public String postNewTodo(@Valid TodoDto1 todo, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return CrudeAppData.TODO;
		todoService.addTodo(todo);
		return REDIRECT_TO_GET_ALL;
	}

	@RequestMapping(CrudeAppData.DELETE_TODO)
	public String deleteTodo(@RequestParam int id) {
		todoService.deleteTodo(id);
		return REDIRECT_TO_GET_ALL;

	}

	@RequestMapping(value = CrudeAppData.UPDATE_TOOD, method = RequestMethod.GET)
	public String updateTodo(@RequestParam int id, ModelMap model) {
		Todo todo = todoService.getTodo(id);
		model.addAttribute(CrudeAppData.TODO, new TodoDto2(todo.getDescription(), todo.getLocalDate(), todo.isDone()));
		model.addAttribute(CrudeAppData.PAGE_TITLE, CrudeAppData.UPDATE_PAGE);
		return CrudeAppData.TODO;
	}

	@RequestMapping(value = CrudeAppData.UPDATE_TOOD, method = RequestMethod.POST)
	public String postUpdateTodo(@RequestParam int id, @Valid TodoDto2 todo, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return CrudeAppData.TODO;
		todoService.updateTodo(id, todo);
		return REDIRECT_TO_GET_ALL;
	}
}
