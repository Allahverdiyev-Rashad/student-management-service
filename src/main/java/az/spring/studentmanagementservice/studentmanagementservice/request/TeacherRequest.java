package az.spring.studentmanagementservice.studentmanagementservice.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRequest {

    @Id
    private Long id;
    private String username;
    private String password;

}