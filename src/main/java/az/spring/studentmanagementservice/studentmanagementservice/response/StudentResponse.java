package az.spring.studentmanagementservice.studentmanagementservice.response;

import lombok.Data;
import javax.persistence.Id;

@Data
public class StudentResponse {

    @Id
    private long id;
    private String name;
    private String surname;
    private String email;

}