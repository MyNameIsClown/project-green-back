package project.groups.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.groups.models.Group;
import project.groups.repo.GroupsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupServiceI{
    @Autowired
    private GroupsRepository repository;
    @Override
    public Group createGroup(Group group) {
        return repository.save(group);
    }

    @Override
    public Optional<Group> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Group> getAll() {
        return repository.findAll();
    }
}
