package com.instacare.patientservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class PatientRequestDTO  {
    @NotBlank(message = "Name is required")
    @Size(max=50, message= "Name cannot exceed 50 characters")
    private String firstName;

    @NotBlank(message = "Name is required")
    @Size(max=50, message= "Name cannot exceed 50 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message= "Invalid email")
    private String email;

    @NotBlank(message =  "Mobile number is required")
    private String mobileNumber;

    @NotBlank(message = "Registered date is required")
    private Date registeredDate;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Date of birth is required")
    private Date dateOfBirth;
}
