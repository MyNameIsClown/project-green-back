package project.carbonFootprint.services;

import project.carbonFootprint.models.CarbonFootprintData;
import project.carbonFootprint.models.dto.CalculationRequest;
import project.carbonFootprint.models.dto.carbonFootprintData.EnergyConsumptionData;
import project.carbonFootprint.models.dto.carbonFootprintData.FoodConsumptionData;
import project.carbonFootprint.models.dto.carbonFootprintData.TransportationUseData;
import project.carbonFootprint.models.dto.carbonFootprintData.WasteProductionData;
import project.users.models.User;

import java.util.List;


public interface CarbonFootprintCalculationI {
    project.carbonFootprint.models.transportation.TransportationUseData calculateTransportEmisions(List<TransportationUseData> transportationUseData);
    project.carbonFootprint.models.energy.EnergyConsumptionData calculateEnergyEmisions(List<EnergyConsumptionData> energyConsumptionData);
    project.carbonFootprint.models.food.FoodConsumptionData calculateFoodEmisions(List<FoodConsumptionData> foodConsumptionData);
    project.carbonFootprint.models.waste.WasteProductionData calculateWasteEmisions(List<WasteProductionData> wasteProductionData);
    CarbonFootprintData calculate(CalculationRequest calculationRequest, User currentUser);
}
