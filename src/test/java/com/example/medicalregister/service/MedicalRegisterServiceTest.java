package com.example.medicalregister.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.medicalregister.entity.MedicalRegister;
import com.example.medicalregister.repository.MedicalRegisterRepository;

@SpringBootTest
public class MedicalRegisterServiceTest {

	@Mock
    private MedicalRegisterRepository repository;
	
	@InjectMocks
	private MedicalRegisterService service;

    @Test
    public void testCreateRecord() {
        MedicalRegister record = new MedicalRegister();
        record.setName("John");
        record.setAge(30);
        record.setMedicalHistory("Good");

        Mockito.when(repository.save(record)).thenReturn(record);

        MedicalRegister saved = service.createRecord(record);
        Assertions.assertEquals("John", saved.getName());
    }

    @Test
    public void testFindById() {
        MedicalRegister record = new MedicalRegister();
        record.setId(1L);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(record));

        MedicalRegister result = service.findById(1L);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getId());
    }
    
    @Test
    public void testFindByIdNotFound() {
    	Long id = 9L;
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
            service.findById(id);
        });

        Assertions.assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }
    
    @Test
    public void testUpdateRecord() {
        MedicalRegister record = new MedicalRegister();
        record.setId(1L);
        record.setName("John Update");
        record.setAge(30);
        record.setMedicalHistory("Good");

        Mockito.when(repository.save(record)).thenReturn(record);

        MedicalRegister saved = service.createRecord(record);
        Assertions.assertEquals("John Update", saved.getName());
    }
    
    @Test
    public void testDeleteRecord() {
    	Long id = 1L;
        service.deleteRecord(id);
        Mockito.verify(repository, Mockito.times(1)).deleteById(id);
    }
    
    @Test
    public void testFindAll() {
		MedicalRegister record = new MedicalRegister();
		record.setName("Chris");
        record.setAge(32);
        record.setMedicalHistory("Good");
        
        List<MedicalRegister> records = List.of(record);
        Mockito.when(repository.findAll()).thenReturn(records);

        List<MedicalRegister> result = service.findAllRecords();

        Assertions.assertEquals(1, result.size());
    }

}
