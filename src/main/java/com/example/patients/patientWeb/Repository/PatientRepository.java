package com.example.patients.patientWeb.Repository;
import com.example.patients.patientWeb.model.Patient;

import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository <Patient, Long>{
    
}
