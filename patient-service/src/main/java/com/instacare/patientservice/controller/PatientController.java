package com.instacare.patientservice.controller;

import com.instacare.patientservice.dto.PatientResponseDTO;
import com.instacare.patientservice.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/patients")
public class PatientController {
    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        List<PatientResponseDTO> patients = patientService.getPatients();
        System.out.printf("getAllPatients: %d\n", patients.size());
        return new ResponseEntity<>(patients, HttpStatus.OK);
//        return ResponseEntity.ok().body(patients);
    }
}
