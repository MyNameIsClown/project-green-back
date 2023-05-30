package project.users.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import project.users.models.Roles;

public interface RoleRepository extends JpaRepository<Roles, Integer> {
}
