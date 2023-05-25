package project.carbonFootprint.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.carbonFootprint.models.CarbonFootprintData;
import project.carbonFootprint.repo.CarbonFootprintDataRepository;

@Service
@RequiredArgsConstructor
public class CarbonFootprintDataServiceImpl implements CarbonFootprintDataServiceI{
    @Autowired
    private CarbonFootprintDataRepository repository;
    @Override
    public void save(CarbonFootprintData carbonFootprintData) {
        repository.save(carbonFootprintData);
    }

    @Override
    public CarbonFootprintData get(Long id) {
        return repository.getReferenceById(id);
    }
}
