package project.carbonFootprint.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import project.carbonFootprint.models.CarbonFootprintData;
import project.carbonFootprint.models.dto.CalculationRequest;
import project.carbonFootprint.models.transportation.TransportationUseData;
import project.carbonFootprint.repo.TransportationUseDataRepository;
import project.carbonFootprint.services.CarbonFootprintDataServiceI;
import project.security.jwt.service.JwtService;
import project.users.models.User;

import java.sql.Date;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/carbonFootprint")
@RequiredArgsConstructor
public class CarbonFootprintCalcController {

    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final CarbonFootprintDataServiceI carbonFootprintDataService;

    @Autowired
    private final TransportationUseDataRepository transportationUseDataRepository;

    @PostMapping("/calculation")
    @ResponseBody
    public ResponseEntity<String> createUserWithUserRole(@RequestBody CalculationRequest calculationRequest, @AuthenticationPrincipal User userLogged) {
        CarbonFootprintData carbonFootprintData = CarbonFootprintData.builder()
                        .user(userLogged)
                        .date(Date.valueOf(LocalDate.now()))
                .build();
        carbonFootprintDataService.save(carbonFootprintData);
        TransportationUseData transportationUseData = TransportationUseData.of(calculationRequest.getTransportationUseData());
        transportationUseData.setCarbonFootprintData(carbonFootprintData);
        carbonFootprintData.setTransportationUseData(transportationUseData);
        carbonFootprintDataService.save(carbonFootprintData);
        return ResponseEntity.status(HttpStatus.OK).body("pùta");
    }
}

