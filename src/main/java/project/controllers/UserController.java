package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import project.models.User;
import project.services.UserServiceI;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserServiceI service;
	
	@RequestMapping("home")
	public @ResponseBody String home() {
		return "Hola mundo";
	}
	
	@GetMapping("user")
	@ResponseBody
	public User getUserById(@RequestParam Long id) {
		return service.getUserById(id);
	}
}
