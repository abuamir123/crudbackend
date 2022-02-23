package com.example.patients.patientWeb.Services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.example.patients.patientWeb.Repository.PatientRepository;
import com.example.patients.patientWeb.model.Patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;



    public Patient savePatient(Patient patient)
    {
        try{
            patientRepository.save(patient);
            return patient;

        }
        catch(Exception ex)
        {
            return null;
        }
    }

    public List<Patient>  loadPatients() 
	{
		try {
			List<Patient> list =  (List<Patient>) patientRepository.findAll();
            System.out.println(list);
			return list;
		}catch(Exception ex) {
			return null;
		}
	}
    public Boolean deletePatient(Patient patient)
    {
        try{
            patientRepository.delete(patient);
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }

    public Patient get(Long id)
    {
        try{
            return patientRepository.findById(id).get();

        }catch(Exception ex)
        {
            return null;
        }
    }
    // public Patient updatePatient(Long id ,Patient patient) 
	// {

    //         // list = list.stream().map(b->{
    //         //     if(b.getId()==patient)
    //         //     {
    //         //         b.setName(patient.getName());
    //         //         b.setEmail(patient.getName());
    //         //     }
    //         //     return b;
            
    //         // }).collect(Collectors.toList());
	// 		patientRepository.save(patient);			
	// 		return patient;
            
		
    // }
	}

