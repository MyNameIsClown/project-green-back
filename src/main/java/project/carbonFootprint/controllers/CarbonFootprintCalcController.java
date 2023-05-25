package project.carbonFootprint.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.carbonFootprint.models.dto.CalculationRequest;
import project.carbonFootprint.models.dto.CalculationResponse;

@RestController
@RequestMapping("/api/carbonFootprint")
@RequiredArgsConstructor
public class CarbonFootprintCalcController {
    @PostMapping("/calculation")
    @ResponseBody
    public ResponseEntity<CalculationResponse> createUserWithUserRole(@RequestBody CalculationRequest calculationRequest) {
        // todo
        return ResponseEntity.status(HttpStatus.CREATED).body(new CalculationResponse());
    }
}
