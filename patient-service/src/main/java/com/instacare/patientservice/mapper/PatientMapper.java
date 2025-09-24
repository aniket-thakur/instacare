package com.instacare.patientservice.mapper;

import com.instacare.patientservice.dto.PatientRequestDTO;
import com.instacare.patientservice.dto.PatientResponseDTO;
import com.instacare.patientservice.model.Patient;

import java.time.LocalDate;

public class PatientMapper {

    public static PatientResponseDTO toPatientResponseDTO(Patient p){
        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
        patientResponseDTO.setId(p.getId().toString());
        patientResponseDTO.setFirstName(p.getName());
        patientResponseDTO.setLastName(p.getSurname());
        patientResponseDTO.setGender(p.getGender());
        patientResponseDTO.setAddress(p.getAddress());
        patientResponseDTO.setEmail(p.getEmail());
        patientResponseDTO.setMobileNumber(p.getMobileNumber());
        patientResponseDTO.setDateOfBirth(p.getDob());
        patientResponseDTO.setRegisteredDate(p.getRegisteredDate());

        return patientResponseDTO;
    }

    public static Patient  toModel(PatientRequestDTO patientRequestDTO){
        Patient patient = new Patient();
        patient.setName(patientRequestDTO.getFirstName());
        patient.setSurname(patientRequestDTO.getLastName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setMobileNumber(patientRequestDTO.getMobileNumber());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));
        patient.setGender(patientRequestDTO.getGender());
        patient.setDob(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        return  patient;
    }
}
