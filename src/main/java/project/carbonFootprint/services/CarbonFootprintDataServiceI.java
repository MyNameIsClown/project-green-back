package project.carbonFootprint.services;

import project.carbonFootprint.models.CarbonFootprintData;
import project.users.models.User;


public interface CarbonFootprintDataServiceI {
    CarbonFootprintData save(CarbonFootprintData carbonFootprintData);
    CarbonFootprintData get(Long id);
    CarbonFootprintData getOfUserLogged(User user);
}
