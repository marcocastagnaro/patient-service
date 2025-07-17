package com.interview.patient_service.dto

import java.time.LocalDate

data class CreatePatientResponseDto(
    val id: Long,

    val name: String,

    val email: String,

    val address: String,

    val phone: String,

    val documentPath: String,

    val createdAt: LocalDate

)

