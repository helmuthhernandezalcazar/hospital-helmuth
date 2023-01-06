package com.helmuth.hospital.api.projection;

import com.helmuth.hospital.api.entity.Patient;
import com.helmuth.hospital.api.entity.Room;
import com.helmuth.hospital.api.entity.Triage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "patientProjection",
types= {Patient.class })
public interface PatientProjection {
    @Value("#{target.id}")
    Long getId();
    String getFirstName();
    String getLastName();
    String getDni();
    String getPhoneNumber();
    Date getHospitalizationDate();
    Date getDischargeDate();
    String getSymptoms();
    String getMedicalDiagnosis();
    @Value("#{target.triage.level}")
    String getTriageLevel();
    @Value("#{target.room?.name != null ? target.room.name : (target.dischargeDate != null ? '' : 'Sala de espera')}")
    String getRoomName();

}
