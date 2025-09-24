package com.instacare.patientservice.controller;

import com.instacare.patientservice.dto.PatientRequestDTO;
import com.instacare.patientservice.dto.PatientResponseDTO;
import com.instacare.patientservice.dto.validations.CreatePatientValidationGroup;
import com.instacare.patientservice.service.PatientService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/patient")
public class PatientController {
    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        List<PatientResponseDTO> patients = patientService.getPatients();
        System.out.printf("getAllPatients: %d\n", patients.size());
//        return new ResponseEntity<>(patients, HttpStatus.OK);
        return ResponseEntity.ok().body(patients);
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(
            @Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO) {

        PatientResponseDTO patient = patientService.createPatient(patientRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(
            /*
            @Validated({Default.class}) -> tells spring to validate the request with the default validation defined
            in request dto class
            */
             @PathVariable UUID id,@Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO patient = patientService.updatePatient(id,patientRequestDTO);
        return ResponseEntity.ok().body(patient);
    }
}
