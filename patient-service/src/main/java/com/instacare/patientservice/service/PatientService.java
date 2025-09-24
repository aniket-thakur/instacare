package com.instacare.patientservice.service;

import com.instacare.patientservice.dto.PatientRequestDTO;
import com.instacare.patientservice.dto.PatientResponseDTO;
import com.instacare.patientservice.mapper.PatientMapper;
import com.instacare.patientservice.model.Patient;
import com.instacare.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // get all patients
    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();

        List<PatientResponseDTO> patientResponseDTOS = patients.stream().map(patient -> PatientMapper.toPatientResponseDTO(patient)).toList();

        return patientResponseDTOS;
    }

    // create patient
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        Patient patient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
        return PatientMapper.toPatientResponseDTO(patient);
    }
}
