package az.spring.studentmanagementservice.studentmanagementservice.dto.request;

import lombok.Data;

@Data
public class StudentCreateRequest {

    private long id;

    private String name;

    private String surname;

    private String email;
}