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
import project.carbonFootprint.models.dto.CarbonFootprintDetail;
import project.carbonFootprint.models.dto.CarbonFootprintShort;
import project.carbonFootprint.repo.TransportationUseDataRepository;
import project.carbonFootprint.services.CarbonFootprintCalculationI;
import project.carbonFootprint.services.CarbonFootprintDataServiceI;
import project.security.jwt.service.JwtService;
import project.users.models.User;
import project.users.models.dto.UserResponse;
import project.users.services.UserServiceI;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/carbonFootprint")
@RequiredArgsConstructor
public class CarbonFootprintCalcController {

    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final CarbonFootprintCalculationI carbonFootprintCalculationService;

    @Autowired
    private final CarbonFootprintDataServiceI carbonFootprintDataServiceI;

    @Autowired
    private final UserServiceI userService;

    @Autowired
    private final TransportationUseDataRepository transportationUseDataRepository;

    @PostMapping("/calculation")
    @ResponseBody
    public ResponseEntity<CalculationResponse> calculateCarbonFootprintData(@RequestBody CalculationRequest calculationRequest, @AuthenticationPrincipal User userLogged) {
        userLogged.setCarbonFootprintIsCalculated(true);
        userLogged = userService.updateUser(userLogged);
        CarbonFootprintData carbonFootprintData = carbonFootprintCalculationService.calculate(calculationRequest, userLogged);
        CalculationResponse response = CalculationResponse.builder()
                .user(UserResponse.convertTo(userLogged))
                .totalCo2Emitted(carbonFootprintData.getCo2Emitted())
                .totalGreenScore(carbonFootprintData.getGreenScore())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/homePageInfo")
    @ResponseBody
    public ResponseEntity<CalculationResponse> getHomePageInfo(@AuthenticationPrincipal User userLogged) {
        userLogged.setCarbonFootprintIsCalculated(true);
        userLogged = userService.updateUser(userLogged);


        CarbonFootprintData carbonFootprintData = carbonFootprintDataServiceI.getOfUserLogged(userLogged);
        CalculationResponse response = CalculationResponse.builder()
                .user(UserResponse.convertTo(userLogged))
                .totalCo2Emitted(carbonFootprintData.getCo2Emitted())
                .totalGreenScore(carbonFootprintData.getGreenScore())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/getAll")
    @ResponseBody
    public ResponseEntity<List<CarbonFootprintShort>> getAllShort(@AuthenticationPrincipal User userLogged){
        List<CarbonFootprintShort> response = carbonFootprintDataServiceI.getAllOfUserLogged(userLogged).stream().map(
                        (data) -> CarbonFootprintShort.of(data)
                ).collect(Collectors.toList()
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    public ResponseEntity<CarbonFootprintDetail> getAllShort(@PathVariable("id") Long id){
        CarbonFootprintDetail response = CarbonFootprintDetail.of(carbonFootprintDataServiceI.get(id));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}

