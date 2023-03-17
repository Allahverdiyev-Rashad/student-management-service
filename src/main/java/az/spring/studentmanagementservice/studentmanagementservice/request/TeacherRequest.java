package az.spring.studentmanagementservice.studentmanagementservice.request;

import az.spring.studentmanagementservice.studentmanagementservice.error.constraint.validation.Password;
import az.spring.studentmanagementservice.studentmanagementservice.error.constraint.validation.Username;
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

    @Username
    private String username;

    @Password
    private String password;

    private Long studentId;

}