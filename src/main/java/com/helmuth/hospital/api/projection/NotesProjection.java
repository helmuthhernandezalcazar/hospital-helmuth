package com.helmuth.hospital.api.projection;


import com.helmuth.hospital.api.model.Note;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "noteProjection",
        types = {Note.class})
public interface NotesProjection {
    String getNote();
    Date getDate();
    @Value("#{target.employee.email}")
    String getEmployeeEmail();
}
