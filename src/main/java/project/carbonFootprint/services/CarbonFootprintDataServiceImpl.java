package project.carbonFootprint.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import project.carbonFootprint.models.CarbonFootprintData;
import project.carbonFootprint.repo.CarbonFootprintDataRepository;
import project.users.models.User;

@Service
@RequiredArgsConstructor
public class CarbonFootprintDataServiceImpl implements CarbonFootprintDataServiceI{
    @Autowired
    private CarbonFootprintDataRepository repository;
    @Override
    public CarbonFootprintData save(CarbonFootprintData carbonFootprintData) {
        return repository.save(carbonFootprintData);
    }

    @Override
    public CarbonFootprintData get(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public CarbonFootprintData getOfUserLogged(User user) {
        return repository.findFirstByUser(user, Sort.by("id").descending());
    }
}
