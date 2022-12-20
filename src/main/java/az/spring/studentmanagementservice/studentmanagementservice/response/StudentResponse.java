package az.spring.studentmanagementservice.studentmanagementservice.response;

import lombok.Data;

@Data
public class StudentResponse {

    private Long id;
    private String name;
    private String surname;
    private String email;

}