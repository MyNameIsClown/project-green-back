package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.models.User;
import project.repositories.UserDAO;

@Service
public class UserServiceImpl implements UserServiceI{
	@Autowired
	UserDAO repository;
	
	public User getUserById(Long id) {
		User user = repository.findById(id).get();
		System.out.println(user.toString());
		return user;
	}
}
