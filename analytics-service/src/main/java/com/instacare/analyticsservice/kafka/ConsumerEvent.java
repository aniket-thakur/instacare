package com.instacare.analyticsservice.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@EnableKafka
@Service
public class ConsumerEvent {

    private static final Logger log = LoggerFactory.getLogger(ConsumerEvent.class);

    @KafkaListener(topics = "patient", groupId = "analytics-service")
    public void createPatientEventListener(byte[] event) {
        try {
            PatientEvent patientEvent = PatientEvent.parseFrom(event);

            log.info("Received Patient Event from Kafka Consumer: " +
                            "PatientId: {} || Name : {} || Event Type: {}",
                    patientEvent.getPatientId(),
                    patientEvent.getName(),
                    patientEvent.getEventType());

        } catch (InvalidProtocolBufferException e) {
            log.error("Error while parsing Patient Event from Kafka Consumer :{}", e.getMessage());
            throw new RuntimeException(e);
        }


    }
}
