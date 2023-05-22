package az.spring.studentmanagementservice.studentmanagementservice.request;

import az.spring.studentmanagementservice.studentmanagementservice.error.constraint.validation.Username;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRegisterRequest {

    @Username
    private String username;

    @Column(columnDefinition = "text")
    private String password;

}
