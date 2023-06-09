package az.spring.studentmanagementservice.studentmanagementservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {

    @Bean
    public NewTopic createTopic(){
        return TopicBuilder.name("teacher-topic").replicas(1).partitions(3).build();
    }
}