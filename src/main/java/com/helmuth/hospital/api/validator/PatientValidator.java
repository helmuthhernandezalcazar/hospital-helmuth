package com.helmuth.hospital.api.validator;

import com.helmuth.hospital.api.entity.Patient;
import com.helmuth.hospital.api.exception.FormDataException;
import com.helmuth.hospital.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("beforeCreatePatient")
public class PatientValidator implements Validator {
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Patient.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Patient patient = (Patient) target;
        List<Patient> patientHistory = patientRepository.findByDni(patient.getDni());
        List<Patient> patientHistoryNotDischarged = patientHistory.stream().filter(p -> !p.getDischarged()).collect(Collectors.toList());
        if(patientHistoryNotDischarged.size() > 0)
            throw new FormDataException("Patient dni already registered");
        if(!patient.getDni().matches("[0-9]{8}[a-zA-z]"))
            throw new FormDataException("Invalid dni");
        if(patient.getFirstName().isBlank())
            throw new FormDataException("First name is mandatory field");
        if(patient.getLastName().isBlank())
            throw new FormDataException("Last name is mandatory field");
        if(patient.getPhoneNumber().isBlank())
            throw new FormDataException("Phone number is mandatory field");
        if(patient.getSymptoms().isBlank())
            throw new FormDataException("Symptoms is mandatory field");
    }
}
