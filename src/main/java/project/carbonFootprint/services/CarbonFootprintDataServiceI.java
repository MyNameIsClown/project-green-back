package project.carbonFootprint.services;

import project.carbonFootprint.models.CarbonFootprintData;


public interface CarbonFootprintDataServiceI {
    void save(CarbonFootprintData carbonFootprintData);
    CarbonFootprintData get(Long id);
}
