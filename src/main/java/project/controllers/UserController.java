package project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import project.models.User;
import project.services.UserServiceI;

@RestController
@RequestMapping("api/user")
public class UserController {
	@Autowired
	UserServiceI service;
	
	@GetMapping("{id}")
	@ResponseBody
	public User getUserById(@PathVariable Long id) {
		System.out.println(service.isPresent(id));
		return service.getUserById(id);
	}
	
	@GetMapping("")
	@ResponseBody
	public List<User> getUsers() {
		return service.getAll();
	}
	
	@PostMapping("/new")
	@ResponseBody
	public void createUser(@RequestBody User user) {
		service.createUser(user);
	}
	
	
}
