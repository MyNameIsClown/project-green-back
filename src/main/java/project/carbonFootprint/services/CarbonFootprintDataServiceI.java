package project.carbonFootprint.services;

import project.carbonFootprint.models.CarbonFootprintData;
import project.users.models.User;

import java.util.List;


public interface CarbonFootprintDataServiceI {
    CarbonFootprintData save(CarbonFootprintData carbonFootprintData);
    CarbonFootprintData get(Long id);
    CarbonFootprintData getOfUserLogged(User user);
    List<CarbonFootprintData> getAllOfUserLogged(User user);
}
