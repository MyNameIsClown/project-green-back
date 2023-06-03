package project.carbonFootprint.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.carbonFootprint.models.CarbonFootprintCalcParameter;
import project.carbonFootprint.models.CarbonFootprintData;
import project.carbonFootprint.models.dto.CalculationRequest;
import project.carbonFootprint.models.energy.EnergyConsumptionData;
import project.carbonFootprint.models.food.FoodConsumptionData;
import project.carbonFootprint.models.transportation.TransportationUseData;
import project.carbonFootprint.models.waste.WasteProductionData;
import project.carbonFootprint.repo.CarbonFooprintCalcParameterRepository;
import project.users.models.User;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CarbonFootprintCalculationImpl implements CarbonFootprintCalculationI{

    private static CarbonFootprintData CARBON_FOOTPRINT_DATA;

    private static final Integer MAX_TRANSPORT_EMISSIONS = 5000;
    private static final Integer MAX_HOME_ENERGY_EMISSIONS = 5000;
    private static final Double MAX_FOOD_EMISSIONS = 1411.2;
    private static final Double MAX_WASTE_EMISSIONS = 1209.6;

    @Autowired
    CarbonFooprintCalcParameterRepository calculationParameterRepo;

    @Autowired
    private CarbonFootprintDataServiceI carbonFootprintDataService;
    @Override
    public TransportationUseData calculateTransportEmisions(List<project.carbonFootprint.models.dto.carbonFootprintData.TransportationUseData> transportationUseData) {
        TransportationUseData response = TransportationUseData.of(transportationUseData);
        response.setCarbonFootprintData(CARBON_FOOTPRINT_DATA);

        AtomicReference<Double> totalCo2Emitted = new AtomicReference<>(0d);

        response.getTransportationUses().forEach((transport)->{
            String vehicleType = transport.getVehicleType();
            CarbonFootprintCalcParameter calculationParameter = calculationParameterRepo.findByName(vehicleType);
            Double emission;
            if(CarbonFootprintCalcParameter.isUnipersonalTransport(vehicleType)){
                emission = (transport.getDistanceTravelInKm()/calculationParameter.getEfficiencyFactor())*calculationParameter.getCo2Factor();
            }else if(CarbonFootprintCalcParameter.isLongTravelTransport(vehicleType) || CarbonFootprintCalcParameter.isPublicTransport(vehicleType)){
                emission = transport.getDistanceTravelInKm()*calculationParameter.getCo2Factor();
            } else {
                emission = 0d;
            }

            totalCo2Emitted.updateAndGet(v -> v + emission);
        });
        response.setGreenScore((int)((totalCo2Emitted.get()*100)/ MAX_TRANSPORT_EMISSIONS));
        response.setCo2Emitted(totalCo2Emitted.get());
        return response;
    }

    @Override
    public EnergyConsumptionData calculateEnergyEmisions(List<project.carbonFootprint.models.dto.carbonFootprintData.EnergyConsumptionData> energyConsumptionData) {
        EnergyConsumptionData response = EnergyConsumptionData.of(energyConsumptionData);
        response.setCarbonFootprintData(CARBON_FOOTPRINT_DATA);

        AtomicReference<Double> totalCo2Emitted = new AtomicReference<>(0d);

        response.getEnergyConsumptions().forEach((energy)->{
            String energyType = energy.getEnergyType();
            CarbonFootprintCalcParameter calculationParameter = calculationParameterRepo.findByName(energyType);
            Double emission = energy.getConsume()*calculationParameter.getCo2Factor();
            totalCo2Emitted.updateAndGet(v -> v + emission);
        });

        response.setGreenScore((int)((totalCo2Emitted.get()*100)/MAX_HOME_ENERGY_EMISSIONS));
        response.setCo2Emitted(totalCo2Emitted.get());
        return response;
    }

    @Override
    public FoodConsumptionData calculateFoodEmisions(List<project.carbonFootprint.models.dto.carbonFootprintData.FoodConsumptionData> foodConsumptionData) {
        FoodConsumptionData response = FoodConsumptionData.of(foodConsumptionData);
        response.setCarbonFootprintData(CARBON_FOOTPRINT_DATA);

        AtomicReference<Double> totalCo2Emitted = new AtomicReference<>(0d);

        response.getFoodConsumptions().forEach((food)->{
            String foodType = food.getFoodType();
            CarbonFootprintCalcParameter calculationParameter = calculationParameterRepo.findByName(foodType);

            Double averageMeatWeightInKgsPerConsumption = 0.3d;
            Double averageMilkWeightInKgsPerConsumption = 0.25d;
            Double averageCalculationData = foodType == "meat" ? averageMeatWeightInKgsPerConsumption : averageMilkWeightInKgsPerConsumption;

            Double emission = food.getConsume()*averageCalculationData*calculationParameter.getCo2Factor();
            totalCo2Emitted.updateAndGet(v -> v + emission);
        });

        response.setGreenScore((int)((totalCo2Emitted.get()*100)/MAX_FOOD_EMISSIONS));
        response.setCo2Emitted(totalCo2Emitted.get());
        return response;
    }

    @Override
    public WasteProductionData calculateWasteEmisions(List<project.carbonFootprint.models.dto.carbonFootprintData.WasteProductionData> wasteProductionData) {
        WasteProductionData response = WasteProductionData.of(wasteProductionData);
        response.setCarbonFootprintData(CARBON_FOOTPRINT_DATA);

        AtomicReference<Double> totalCo2Emitted = new AtomicReference<>(0d);

        response.getWasteProductions().forEach((waste)->{
            String energyType = waste.getWasteType();
            CarbonFootprintCalcParameter calculationParameter = calculationParameterRepo.findByName(energyType);
            Double emission = waste.getConsume()*calculationParameter.getCo2Factor();
            totalCo2Emitted.updateAndGet(v -> v + emission);
        });

        response.setGreenScore((int) ((totalCo2Emitted.get()*100)/MAX_WASTE_EMISSIONS));
        response.setCo2Emitted(totalCo2Emitted.get());
        return response;
    }

    public CarbonFootprintData calculate(CalculationRequest calculationRequest, User currentUser) {

        CARBON_FOOTPRINT_DATA = carbonFootprintDataService.save(CarbonFootprintData.builder()
                .user(currentUser)
                .date(Date.valueOf(LocalDate.now()))
                .build());

        TransportationUseData transportationUseData = calculateTransportEmisions(calculationRequest.getTransportationUseData());
        EnergyConsumptionData energyConsumptionData = calculateEnergyEmisions(calculationRequest.getEnergyConsumptionData());
        FoodConsumptionData foodConsumptionData = calculateFoodEmisions(calculationRequest.getFoodConsumptionData());
        WasteProductionData wasteProductionData = calculateWasteEmisions(calculationRequest.getWasteProductionData());

        Double emissions = transportationUseData.getCo2Emitted() + energyConsumptionData.getCo2Emitted() + foodConsumptionData.getCo2Emitted() + wasteProductionData.getCo2Emitted();
        Integer totalGreenScore = (transportationUseData.getGreenScore()*2 + energyConsumptionData.getGreenScore()*3 + foodConsumptionData.getGreenScore()*1 + wasteProductionData.getGreenScore()*1)/7;

        CARBON_FOOTPRINT_DATA.setCo2Emitted(emissions);
        CARBON_FOOTPRINT_DATA.setGreenScore(totalGreenScore);
        CARBON_FOOTPRINT_DATA.setTransportationUseData(transportationUseData);
        CARBON_FOOTPRINT_DATA.setEnergyConsumptionData(energyConsumptionData);
        CARBON_FOOTPRINT_DATA.setFoodConsumptionData(foodConsumptionData);
        CARBON_FOOTPRINT_DATA.setWasteProductionData(wasteProductionData);

        return carbonFootprintDataService.save(CARBON_FOOTPRINT_DATA);
    }
}
