package project.activities.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.activities.model.Activity;
import project.activities.model.Invitation;
import project.activities.repo.InvitationRepository;
import project.users.models.User;

import java.util.List;
import java.util.Optional;

@Service
public class InvitationServiceImpl implements InvitationServiceI{
    @Autowired
    InvitationRepository repository;
    @Override
    public List<Invitation> getAllByActivity(Activity activity) {
        return repository.findByActivity(activity);
    }

    @Override
    public boolean existInvitationOf(Activity activity, User user) {
        return repository.existsByActivityAndUser(activity, user);
    }

    @Override
    public Invitation save(Invitation invitation) {
        return repository.save(invitation);
    }

    @Override
    public Optional<Invitation> getOne(Activity activity, User user) {
        return repository.findByActivityAndUser(activity, user);
    }

    @Override
    public void deleteJoin(Invitation invitation) {
        repository.delete(invitation);
    }

    @Override
    public List<Invitation> getAllByUser(User user) {
        return repository.findByUser(user);
    }

    @Override
    public void deleteAll(List<Invitation> invitations) {
        repository.deleteAll(invitations);
    }
}
