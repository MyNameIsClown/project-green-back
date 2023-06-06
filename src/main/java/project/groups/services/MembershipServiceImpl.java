package project.groups.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.groups.models.Membership;
import project.groups.repo.MembershipRepository;

@Service
public class MembershipServiceImpl implements MembershipServiceI{

    @Autowired
    private MembershipRepository repository;

    @Override
    public Membership suscribe(Membership membership) {
        return repository.save(membership);
    }
}
