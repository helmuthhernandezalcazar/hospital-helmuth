package com.helmuth.hospital.api.validator;

import com.helmuth.hospital.api.entity.Measurement;
import com.helmuth.hospital.api.exception.FormDataException;
import com.helmuth.hospital.api.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("beforeCreateMeasurement")
public class MeasurementValidator implements Validator {
    @Autowired
    private MeasurementRepository measurementRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;
        String measurementType = measurement.getMeasurementType().getMeasurementType();
        String measurementValue = measurement.getMeasurementValue();
        switch (measurementType) {
            case "Temperatura":
                double temperatureValue = Double.parseDouble(measurementValue);
                if (temperatureValue < 20 || temperatureValue > 50)
                    throw new FormDataException("Error in temperature value");
                break;
            case "Peso":
                double weightValue = Double.parseDouble(measurementValue);
                if (weightValue < 0 || weightValue > 999)
                    throw new FormDataException("Error in weight value");
                break;
            case "Presión sanguinea":
                if (!measurementValue.matches("[0-9]{1,3}/[0-9]{1,3}"))
                    throw new FormDataException("Error in blood pressure format");
                String[] pressures = measurementValue.split("/");
                if (Integer.parseInt(pressures[0]) < 0 || Integer.parseInt(pressures[0]) > 240 ||
                        Integer.parseInt(pressures[1]) < 0 || Integer.parseInt(pressures[1]) > 240)
                    throw new FormDataException("Error in blood pressure value");
                break;
            default:
                break;
        }
    }
}
