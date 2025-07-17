package com.interview.patient_service.service

import com.interview.patient_service.dto.CreatePatientDto
import com.interview.patient_service.dto.CreatePatientResponseDto
import com.interview.patient_service.entity.Patient
import com.interview.patient_service.repository.PatientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class PatientService {
    @Autowired
    private lateinit var patientRepository: PatientRepository
@Autowired
private lateinit var mailSenderService: MailService
    fun createPatient(dto: CreatePatientDto): CreatePatientResponseDto {
        if (patientRepository.findByEmail(dto.email).isPresent) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already registered")
        }
        val entity = Patient.from(dto)
        val saved = patientRepository.save(entity)
        mailSenderService.sendConfirmationEmail(saved.email, saved.name)
        return saved.toResponseDto()
    }

    fun getPatient(id: Long): CreatePatientResponseDto {
        val patient = patientRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found") }
        return patient.toResponseDto()
    }
}
