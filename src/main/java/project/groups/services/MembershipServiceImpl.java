package project.groups.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.groups.models.Group;
import project.groups.models.Membership;
import project.groups.repo.MembershipRepository;
import project.users.models.User;

import java.util.List;

@Service
public class MembershipServiceImpl implements MembershipServiceI{

    @Autowired
    private MembershipRepository repository;

    @Override
    public Membership suscribe(Membership membership) {
        return repository.save(membership);
    }

    @Override
    public List<Membership> getMembersOf(Group group) {
        return repository.findByGroup(group);
    }

    @Override
    public boolean existMembershipOf(Group group, User user) {
        return repository.existsByGroupAndUser(group, user);
    }


}
