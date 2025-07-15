package com.example.medicalregister.service;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.medicalregister.entity.MedicalRegister;
import com.example.medicalregister.repository.MedicalRegisterRepository;

@Service
public class MedicalRegisterService {

	@Autowired
	private MedicalRegisterRepository medicalRepository;
	
	private final Logger logger = LogManager.getLogger(this.getClass());
	
	public MedicalRegister createRecord(MedicalRegister record) {
		logger.info("Saving record: {}", record);
		return medicalRepository.save(record);
    }
	
	public MedicalRegister findById(Long id) {
		logger.info("Finding record with ID: {}", id);
	
        return medicalRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public MedicalRegister updateRecord(MedicalRegister record) {
    	logger.info("Updating record: {}", record);

    	return medicalRepository.save(record);
    }

    public void deleteRecord(Long id) {
    	logger.info("Deleting record with ID: {}", id);
    	medicalRepository.deleteById(id);
    }

    public List<MedicalRegister> findAllRecords() {
    	logger.info("Finding all medical records");
    	return medicalRepository.findAll();
    }

}
