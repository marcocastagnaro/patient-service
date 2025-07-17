package com.interview.patient_service.dto
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class CreatePatientDto(
    @field:NotBlank @field:Size(max = 100)
    val name: String,

    @field:NotBlank @field:Email
    val email: String,

    @field:NotBlank @field:Size(max = 200)
    val address: String,

    @field:NotBlank
    @field:Pattern(
        regexp = "^\\+\\d{1,3}\\s?\\d{4,14}\$",
        message = "Phone must be in format +<countrycode> <number>"
    )
    val phone: String,

    @field:NotBlank
    @field:Pattern(
        regexp = "^/.+\\.(pdf|jpg|jpeg)\$",
        message = "documentPath must be a /path/to/file.pdf or .jpg"
    )
    val documentPath: String
)
