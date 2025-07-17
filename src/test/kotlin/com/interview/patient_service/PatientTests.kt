package com.interview.patient_service.service
import com.interview.patient_service.dto.CreatePatientDto
import com.interview.patient_service.entity.Patient
import com.interview.patient_service.repository.PatientRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate
import java.util.*

@ExtendWith(MockitoExtension::class)
class PatientServiceTest {

    @Mock
    lateinit var patientRepository: PatientRepository

    @Mock
    lateinit var mailService: MailService

    @InjectMocks
    lateinit var patientService: PatientService

    @Test
    fun `createPatient should throw BAD_REQUEST when email already exists`() {
        val dto = CreatePatientDto("Juan", "x@x.com", "dir", "tel", "doc")
        val existing = Patient.from(dto)
        given(patientRepository.findByEmail(dto.email))
            .willReturn(Optional.of(existing))

        val ex = assertThrows<ResponseStatusException> {
            patientService.createPatient(dto)
        }
        assertEquals(HttpStatus.BAD_REQUEST, ex.statusCode)
    }

    @Test
    fun `getPatient should return response when patient exists`() {
        val id = 3L
        val patient = Patient(
            id = id,
            name = "Luis Gómez",
            email = "luis@example.com",
            address = "Calle Real 789",
            phone = "555123456",
            documentPath = "dni-luis.jpg",
            createdAt = LocalDate.now()
        )
        given(patientRepository.findById(id))
            .willReturn(Optional.of(patient))

        val response = patientService.getPatient(id)

        assertNotNull(response)
        assertEquals(patient.id, response.id)
        assertEquals(patient.name, response.name)
    }

    @Test
    fun `getPatient should throw NOT_FOUND when not found`() {
        val id = 99L
        given(patientRepository.findById(id)).willReturn(Optional.empty())

        val ex = assertThrows<ResponseStatusException> {
            patientService.getPatient(id)
        }
        assertEquals(HttpStatus.NOT_FOUND, ex.statusCode)
    }
}
