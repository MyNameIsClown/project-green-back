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
import project.carbonFootprint.models.dto.CalculationResponse;
import project.carbonFootprint.repo.TransportationUseDataRepository;
import project.carbonFootprint.services.CarbonFootprintCalculationI;
import project.security.jwt.service.JwtService;
import project.users.models.User;
import project.users.models.dto.UserResponse;

@RestController
@RequestMapping("/api/carbonFootprint")
@RequiredArgsConstructor
public class CarbonFootprintCalcController {

    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final CarbonFootprintCalculationI carbonFootprintDataService;
    @Autowired
    private final TransportationUseDataRepository transportationUseDataRepository;

    @PostMapping("/calculation")
    @ResponseBody
    public ResponseEntity<CalculationResponse> createUserWithUserRole(@RequestBody CalculationRequest calculationRequest, @AuthenticationPrincipal User userLogged) {
        CarbonFootprintData carbonFootprintData = carbonFootprintDataService.calculate(calculationRequest, userLogged);
        CalculationResponse response = CalculationResponse.builder()
                .user(UserResponse.convertTo(userLogged))
                .totalCo2Emitted(carbonFootprintData.getCo2Emitted())
                .totalGreenScore(carbonFootprintData.getGreenScore())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

