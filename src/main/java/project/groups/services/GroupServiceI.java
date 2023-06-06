package project.groups.services;

import project.groups.models.Group;

import java.util.List;
import java.util.Optional;

public interface GroupServiceI {
    Group createGroup(Group group);
    Optional<Group> getById(Long id);
    List<Group> getAll();
}
