package com.example.medicalregister.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.medicalregister.entity.MedicalRegister;

@Repository
public interface MedicalRegisterRepository extends JpaRepository<MedicalRegister, Long> {

}
