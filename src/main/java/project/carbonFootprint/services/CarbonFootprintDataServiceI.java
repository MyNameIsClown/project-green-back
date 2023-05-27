package project.carbonFootprint.services;

import project.carbonFootprint.models.CarbonFootprintData;


public interface CarbonFootprintDataServiceI {
    CarbonFootprintData save(CarbonFootprintData carbonFootprintData);
    CarbonFootprintData get(Long id);
}
