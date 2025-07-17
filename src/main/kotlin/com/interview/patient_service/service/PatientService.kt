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
import java.util.regex.Pattern


@Service
class PatientService {
    @Autowired
    private lateinit var patientRepository: PatientRepository
@Autowired
private lateinit var mailSenderService: MailService
    private val emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    private val phonePattern = Pattern.compile("^\\+\\d{1,3}\\s?\\d{4,14}\$")

    fun createPatient(dto: CreatePatientDto): CreatePatientResponseDto {
        if (!emailPattern.matcher(dto.email).matches()) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email format")
        }

        if (!phonePattern.matcher(dto.phone).matches()) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Invalid phone format. Expected +<countrycode> <number>"
            )
        }
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
