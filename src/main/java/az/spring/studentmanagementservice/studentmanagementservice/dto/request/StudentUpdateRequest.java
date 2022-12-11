package az.spring.studentmanagementservice.studentmanagementservice.dto.request;

import lombok.Data;

import javax.persistence.Id;

@Data
public class StudentUpdateRequest {

    @Id
    private long id;

    private String name;

    private String surname;

    private String email;
}