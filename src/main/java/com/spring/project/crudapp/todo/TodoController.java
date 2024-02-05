package com.spring.project.crudapp.todo;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes(names = "name")
public class TodoController {
	private TodoService todoService;
	private String username = "Malik";

	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	@RequestMapping("")
	public String mainPage() {
		return "redirect:list-todos";
	}

	@RequestMapping("list-todos")
	public String listAlltodos(ModelMap model) {
		List<Todo> todos = todoService.findByUsername(username);
		Collections.sort(todos, new TodoComparator());
		model.addAttribute("todos", todos);
		return "list-todos";
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.GET)
	public String newTodo(ModelMap model) {
		TodoDTO todo = new TodoDTO("", LocalDate.now());
		model.addAttribute("todo", todo);
		model.addAttribute("title", "Add");
		return "todo";
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.POST)
	public String postNewTodo(@Valid TodoDTO todo, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "todo";
		todoService.addTodo(username, todo.description(), todo.localDate(), false);
		return "redirect:list-todos";
	}

	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		todoService.deleteById(id);
		return "redirect:list-todos";

	}

	@RequestMapping(value = "update-todo", method = RequestMethod.GET)
	public String updateTodo(@RequestParam int id, ModelMap model) {
		Todo todo = todoService.findById(id);
		model.addAttribute("todo", new TodoUpdateDTO(todo.description(), todo.localDate(), todo.done()));
		model.addAttribute("title", "Update");
		return "todo";
	}

	@ModelAttribute("isDoneOptions")
	public Map<Integer, String> populateSubCategoryTypes() {
		Map<Integer, String> subCategoryNameMap = new HashMap<Integer, String>();
		subCategoryNameMap.put(1, Boolean.FALSE.toString());
		subCategoryNameMap.put(2, Boolean.TRUE.toString());
		return subCategoryNameMap;
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.POST)
	public String postUpdateTodo(@RequestParam int id, @Valid TodoUpdateDTO todo, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "todo";
		todoService.updateTodo(new Todo(id, username, todo.description(), todo.localDate(), todo.done()));
		return "redirect:list-todos";
	}
}
