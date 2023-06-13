package project.activities.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.activities.model.Activity;
import project.activities.model.dto.ActivityCreationRequest;
import project.activities.repo.ActivityRepository;
import project.groups.models.Group;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityServiceI{
    @Autowired
    private ActivityRepository repository;
    @Override
    public Optional<Activity> getOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Activity> getAllByGroup(Group group) {
        return repository.findByGroup(group);
    }

    @Override
    public Activity createActivity(ActivityCreationRequest creationRequest, Group group) {
        Activity activity = Activity.of(creationRequest);
        activity.setCreationDate(LocalDateTime.now());
        activity.setGroup(group);
        return repository.save(activity);
    }

    @Override
    public List<Activity> getAllPublic(Group group) {
        return repository.findByGroup(group).stream().filter((activity -> activity.isPrivate()==false)).collect(Collectors.toList());
    }

    @Override
    public Activity save(Activity activity) {
        return repository.save(activity);
    }
}
