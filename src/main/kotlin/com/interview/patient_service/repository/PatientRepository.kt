package com.interview.patient_service.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface PatientRepository : JpaRepository<com.interview.patient_service.entity.Patient, Long> {
    fun findByEmail(email: String): Optional<com.interview.patient_service.entity.Patient>

}