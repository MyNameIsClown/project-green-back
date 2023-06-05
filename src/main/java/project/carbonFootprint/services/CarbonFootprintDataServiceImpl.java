package project.carbonFootprint.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import project.carbonFootprint.models.CarbonFootprintData;
import project.carbonFootprint.repo.CarbonFootprintDataRepository;
import project.users.models.User;

import java.util.List;

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
        return repository.findById(id).orElse(null);
    }

    @Override
    public CarbonFootprintData getOfUserLogged(User user) {
        return repository.findFirstByUser(user, Sort.by("id").descending());
    }

    public List<CarbonFootprintData> getAllOfUserLogged (User user){
        return repository.findAllByUser(user, Sort.by("date").descending());
    }
}
