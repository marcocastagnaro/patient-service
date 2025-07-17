package com.interview.patient_service.service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class MailService(
    @Autowired
    private val mailSender: JavaMailSender
) {
    @Async("taskExecutor")
    fun sendConfirmationEmail(to: String, name: String) {
        val msg = SimpleMailMessage()
        msg.setTo(to)
        msg.setSubject("Registro de paciente exitoso")
        msg.setText("Hola $name,\n\nTu registro en Patient Service API ha sido exitoso.\nGracias por confiar en nosotros.\n\nSaludos.")
        mailSender.send(msg)
    }
}