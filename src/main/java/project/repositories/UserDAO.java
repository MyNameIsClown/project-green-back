package project.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.models.User;

@Repository
public interface UserDAO extends JpaRepository<User, Long>{

	Optional<User> findById(Long id);
}
