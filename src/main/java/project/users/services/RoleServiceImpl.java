package project.users.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.users.models.Role;
import project.users.repo.RoleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleServiceI{
    @Autowired
    private RoleRepository repo;

    @Override
    public void createRoles(List<Role> role) {
        repo.saveAll(role);
    }
}
