package com.example.medicalregister.bean;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.medicalregister.entity.MedicalRegister;
import com.example.medicalregister.service.MedicalRegisterService;

@ExtendWith(MockitoExtension.class)
public class MedicalRegisterBeanTest {

	@InjectMocks
    private MedicalRegisterBean bean;

    @Mock
    private MedicalRegisterService service;
    
    @Test
    public void testInit() {
        List<MedicalRegister> records = List.of(new MedicalRegister());
        Mockito.when(service.findAllRecords()).thenReturn(records);

        bean.init();

        Assertions.assertEquals(1, bean.getRecords().size());
        Mockito.verify(service).findAllRecords();
    }

    @Test
    public void testSave() {
        MedicalRegister record = new MedicalRegister();
        record.setName("Emily");
        record.setAge(30);
        record.setMedicalHistory("Goodd");
        bean.setMedicalRecord(record);

        List<MedicalRegister> records = List.of(record);
        Mockito.when(service.findAllRecords()).thenReturn(records);

        bean.save();

        Mockito.verify(service).createRecord(record);
        Assertions.assertNotNull(bean.getMedicalRecord());
        Assertions.assertEquals(1, bean.getRecords().size());
    }

    @Test
    public void testEdit() {
        Long id = 1L;
        MedicalRegister mockRecord = new MedicalRegister();
        mockRecord.setId(id);

        Mockito.when(service.findById(id)).thenReturn(mockRecord);

        String result = bean.edit(id);

        Assertions.assertEquals("medical-register-edit.xhtml?faces-redirect=true", result);
        Assertions.assertEquals(id, bean.getSelectedRecord().getId());
    }

    @Test
    public void testUpdate() {
        MedicalRegister record = new MedicalRegister();
        record.setId(2L);
        record.setName("Emily Update");
        record.setAge(30);
        record.setMedicalHistory("Excellent"); 
        
        bean.setSelectedRecord(record);

        List<MedicalRegister> records = List.of(record);
        Mockito.when(service.findAllRecords()).thenReturn(records);

        String result = bean.update();

        Mockito.verify(service).updateRecord(record);
        Assertions.assertEquals("medical-register.xhtml?faces-redirect=true", result);
        Assertions.assertEquals(1, bean.getRecords().size());
    }

    @Test
    public void testDelete() {
        Long id = 3L;
        List<MedicalRegister> records = List.of();

        Mockito.when(service.findAllRecords()).thenReturn(records);

        bean.delete(id);

        Mockito.verify(service).deleteRecord(id);
        Mockito.verify(service).findAllRecords();
        Assertions.assertEquals(0, bean.getRecords().size());
    }
    
}
