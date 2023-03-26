package project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.models.User;
import project.repositories.UserDAO;

@Service
public class UserServiceImpl implements UserServiceI{
	@Autowired
	UserDAO repository;
	
	public User getUserById(Long id) {
		return repository.findById(id).get();
	}

	public List<User> getAll() {
		return repository.findAll();
	}

	@Override
	public void createUser(User user) {
		repository.save(user);
	}
	
	public Boolean isPresent(Long id) {
		return repository.existsById(id);
	}
}
