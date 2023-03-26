package project.services;

import java.util.List;

import project.models.User;

public interface UserServiceI {
	public User getUserById(Long id);
	public List<User> getAll();
	public void createUser(User user);
	public Boolean isPresent(Long id);
}
