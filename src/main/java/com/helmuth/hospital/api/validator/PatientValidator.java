package com.helmuth.hospital.api.validator;

import com.helmuth.hospital.api.model.Patient;
import com.helmuth.hospital.api.exception.FormDataException;
import com.helmuth.hospital.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
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
            throw new FormDataException("Ya existe un paciente registrado con ese dni");
        if(!patient.getDni().matches("[0-9]{8}[a-zA-z]"))
            throw new FormDataException("Dni no válido");
        if(patient.getFirstName().isBlank())
            throw new FormDataException("El nombre es obligatorio");
        if(patient.getLastName().isBlank())
            throw new FormDataException("El apellido es obligatorio");
        if(patient.getPhoneNumber().isBlank())
            throw new FormDataException("El número de teléfono es obligatorio");
        if(patient.getSymptoms().isBlank())
            throw new FormDataException("Los síntomas son obligatorios");
    }
}
