package az.spring.studentmanagementservice.studentmanagementservice.request;

import lombok.Data;
import javax.persistence.Id;

@Data
public class StudentRequest {

    @Id
    private long id;
    private String name;
    private String surname;
    private String email;

}