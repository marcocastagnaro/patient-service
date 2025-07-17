package com.interview.patient_service.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.PrePersist
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "patients")
class Patient(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false)
    var address: String,

    @Column(nullable = false)
    var phone: String,

    @Column(nullable = false)
    var documentPath: String,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDate? = null
) {
    @PrePersist
    fun onPrePersist() {
        createdAt = LocalDate.now()
    }

    companion object {
        fun from(dto: com.interview.patient_service.dto.CreatePatientDto): Patient {
            return Patient(
                name = dto.name,
                email = dto.email,
                address = dto.address,
                phone = dto.phone,
                documentPath = dto.documentPath
            )
        }
    }

    fun toResponseDto(): com.interview.patient_service.dto.CreatePatientResponseDto {
        return com.interview.patient_service.dto.CreatePatientResponseDto(
            id = this.id!!,
            name = this.name,
            email = this.email,
            address = this.address,
            phone = this.phone,
            documentPath = this.documentPath,
            createdAt = this.createdAt!!
        )
    }
}