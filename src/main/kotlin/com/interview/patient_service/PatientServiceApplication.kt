package com.interview.patient_service
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class PatientServiceApplication

fun main(args: Array<String>) {
	runApplication<PatientServiceApplication>(*args)
}
