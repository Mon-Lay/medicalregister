package com.example.medicalregister.bean;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.medicalregister.entity.MedicalRegister;
import com.example.medicalregister.service.MedicalRegisterService;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Named
@SessionScoped
public class MedicalRegisterBean implements Serializable{

private static final long serialVersionUID = 7546986257419281892L;

	@Autowired
    private MedicalRegisterService service;
	
	private final Logger logger = LogManager.getLogger(this.getClass());

    private List<MedicalRegister> records;
    private MedicalRegister medicalRecord = new MedicalRegister();
    private MedicalRegister selectedRecord = new MedicalRegister();
    
    @PostConstruct
    public void init() {
        records = service.findAllRecords();
        logger.info("Fetching all medical records", records);
    }

    public void save() {
        service.createRecord(medicalRecord);
        medicalRecord = new MedicalRegister();
        loadRecords();
    }
    
    public String edit(Long id) {
    	selectedRecord = service.findById(id);
        return "medical-register-edit.xhtml?faces-redirect=true";
    }
    
    public String update() {
        service.updateRecord(selectedRecord);
        medicalRecord = new MedicalRegister();
        selectedRecord = new MedicalRegister();
        loadRecords();
        return "medical-register.xhtml?faces-redirect=true";
    }

    public void delete(Long id) {
        service.deleteRecord(id);
        loadRecords();
    }
    
    private void loadRecords() {
    	records = service.findAllRecords();
    }

    public MedicalRegister getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRegister medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public List<MedicalRegister> getRecords() {
        return records;
    }

	public MedicalRegister getSelectedRecord() {
		return selectedRecord;
	}

	public void setSelectedRecord(MedicalRegister selectedRecord) {
		this.selectedRecord = selectedRecord;
	}

}

