package com.interview.patient_service.controller

import com.interview.patient_service.dto.CreatePatientDto
import com.interview.patient_service.dto.CreatePatientResponseDto
import com.interview.patient_service.service.PatientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Validated
@RestController
@RequestMapping("/patient")
class PatientController {
    @Autowired
    private lateinit var patientService: PatientService

    @PostMapping
    fun createPatient(@RequestBody dto: CreatePatientDto): CreatePatientResponseDto {
        return patientService.createPatient(dto)
    }

    @GetMapping("/{id}")
    fun getPatient(@PathVariable id: Long): CreatePatientResponseDto {
        return patientService.getPatient(id)
    }
}
