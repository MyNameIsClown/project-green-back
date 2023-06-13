package project.groups.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.activities.model.Invitation;
import project.activities.services.InvitationServiceI;
import project.groups.models.Group;
import project.groups.models.GroupRoleTypes;
import project.groups.models.Membership;
import project.groups.repo.MembershipRepository;
import project.users.models.User;

import java.util.List;
import java.util.Optional;

@Service
public class MembershipServiceImpl implements MembershipServiceI{

    @Autowired
    private MembershipRepository repository;
    @Autowired
    private InvitationServiceI invitationServiceI;

    @Override
    public Membership suscribe(Membership membership) {
        return repository.save(membership);
    }

    @Override
    public void unsubscribe(Membership membership) {
        repository.delete(membership);
    }

    @Override
    public Optional<Membership> getOne(Group group, User user) {
        return repository.findByGroupAndUser(group, user);
    }

    @Override
    public List<Membership> getMembersOf(Group group) {
        return repository.findByGroup(group);
    }

    @Override
    public boolean existMembershipOf(Group group, User user) {
        return repository.existsByGroupAndUser(group, user);
    }
    @Override
    public void unsubscribe(Group group, User user) {
        List<Invitation> invitations = invitationServiceI.getAllByUser(user);
        invitationServiceI.deleteAll(invitations);
        repository.delete(repository.findByGroupAndUser(group, user).get());
    }

    @Override
    public User getOwner(Group group) {
        List<Membership> memberships = repository.findByGroup(group);
        Optional<Membership> membershipOwner = memberships.stream().filter((membership -> membership.getGroupRole().equals(GroupRoleTypes.OWNER.getValue())))
                .findFirst();
        return membershipOwner.isPresent() ? membershipOwner.get().getUser() : User.builder().build();
    }

    @Override
    public Group getOwn(User user) {
        List<Membership> memberships = repository.findByUser(user);
        Optional<Membership> membershipOwner = memberships.stream().filter((membership -> membership.getGroupRole().equals(GroupRoleTypes.OWNER.getValue())))
                .findFirst();
        return membershipOwner.isPresent() ? membershipOwner.get().getGroup() : null;
    }


}
