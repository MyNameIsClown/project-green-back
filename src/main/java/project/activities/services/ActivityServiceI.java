package project.activities.services;

import project.activities.model.Activity;
import project.activities.model.dto.ActivityCreationRequest;
import project.groups.models.Group;

import java.util.List;
import java.util.Optional;

public interface ActivityServiceI {
    Optional<Activity> getOne(Long id);
    List<Activity> getAllByGroup(Group group);
    Activity createActivity(ActivityCreationRequest creationRequest, Group group);
    List<Activity> getAllPublic();
}
