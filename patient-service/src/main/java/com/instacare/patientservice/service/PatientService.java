package com.instacare.patientservice.service;

import com.instacare.patientservice.dto.PatientRequestDTO;
import com.instacare.patientservice.dto.PatientResponseDTO;
import com.instacare.patientservice.exceptions.EmailAlreadyExistsException;
import com.instacare.patientservice.exceptions.UserNotFoundException;
import com.instacare.patientservice.mapper.PatientMapper;
import com.instacare.patientservice.model.Patient;
import com.instacare.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException(patientRequestDTO.getEmail());
        }
        Patient patient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
        return PatientMapper.toPatientResponseDTO(patient);
    }

    // update Patient
    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
        Patient patient = patientRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("user not found with id: " + id));
        if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(),id)) {
            throw new EmailAlreadyExistsException(patientRequestDTO.getEmail());
        }
        patient.setName(patientRequestDTO.getFirstName());
        patient.setSurname(patientRequestDTO.getLastName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDob(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        patient.setGender(patientRequestDTO.getGender());
        patient.setMobileNumber(patientRequestDTO.getMobileNumber());
        patient.setAddress(patientRequestDTO.getAddress());

        patientRepository.save(patient);
        return PatientMapper.toPatientResponseDTO(patient);
    }

    // Delete Patient
    public void deletePatient(UUID id){
        patientRepository.deleteById(id);
    }
}
