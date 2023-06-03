package az.spring.studentmanagementservice.studentmanagementservice.kafka;

import az.spring.studentmanagementservice.studentmanagementservice.response.TeacherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaListeners {

    @KafkaListener(topics = "teacher-topic", groupId = "groupId")
    void listener(TeacherResponse teacherResponse) {
        log.info("Listener received data : {}", teacherResponse);
    }

}