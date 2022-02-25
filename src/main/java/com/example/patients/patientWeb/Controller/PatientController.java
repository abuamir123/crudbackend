package com.example.patients.patientWeb.Controller;

import java.util.List;
import java.util.Optional;

import javax.el.PropertyNotFoundException;

import com.example.patients.patientWeb.Repository.PatientRepository;
import com.example.patients.patientWeb.Services.PatientService;
import com.example.patients.patientWeb.model.Patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/patient")

public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientRepository patientRepository;

    @PostMapping("/save")
    public ResponseEntity savePatient(@RequestBody Patient patient)
    {
      System.out.println(patient);
      Patient newPatient=patientService.savePatient(patient);
      if(newPatient==null)
      {
          return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
      }
      else
      {
          return  ResponseEntity.ok(newPatient);
      }
    }

    @GetMapping("/load")
    public List<Patient> loadPatients()
    {
       return patientService.loadPatients();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePatient(@PathVariable Long id)
    {
        System.out.println(id);
         Patient patient =patientService.get(id);
         if(patient==null)
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);
          else   
              {  
                  boolean result=patientService.deletePatient(patient);
                  if(result)    
                            return new ResponseEntity<>(HttpStatus.OK);
                  else
                        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);

              }    
    }

    // @PutMapping("/update/{id}")
	// public Patient updatePatient(@PathVariable("id") Long id,@RequestBody Patient patient) 
	// {		
    //         //  this.patientService.updatePatient(id, patient);
    //         //  return patient;
	// 	Patient newPatient = patientService.get(id);	
	// 	if(newPatient==null)
	// 		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);		
	// 	else
	// 		return ResponseEntity.ok(newPatient);
	// }
    @PutMapping("/update/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient){
		Patient patients = patientRepository.findById(id)
				.orElseThrow();
		
		patients.setName(patient.getName());
		patients.setEmail(patient.getEmail());
		
		Patient updatePatient = patientRepository.save(patients);
		return ResponseEntity.ok(updatePatient);
	}


    @GetMapping("/patient/{id}")
  public Patient retrieveStudent(@PathVariable long id) {
    Optional<Patient> patient = patientRepository.findById(id);
    if (!patient.isPresent()){
    throw new PropertyNotFoundException("id-" + id);}

    return patient.get();
  }
}
    

