package com.instacare.patientservice.mapper;

import com.instacare.patientservice.dto.PatientResponseDTO;
import com.instacare.patientservice.model.Patient;

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

        return patientResponseDTO;
    }
}
