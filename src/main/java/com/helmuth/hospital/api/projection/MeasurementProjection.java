package com.helmuth.hospital.api.projection;

import com.helmuth.hospital.api.model.Measurement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "measurementProjection",
types = {Measurement.class})
public interface MeasurementProjection {
    String getMeasurementValue();
    Date getDate();
    @Value("#{target.measurementType.measurementType}")
    String getMeasurementType();
    @Value("#{target.measurementType.unit}")
    String getMeasurementTypeUnit();

    @Value("#{target.employee.email}")
    String getEmployeeEmail();
}
