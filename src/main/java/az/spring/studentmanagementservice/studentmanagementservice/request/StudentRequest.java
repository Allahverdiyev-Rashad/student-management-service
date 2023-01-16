package az.spring.studentmanagementservice.studentmanagementservice.request;

import az.spring.studentmanagementservice.studentmanagementservice.error.constraint.validation.Name;
import az.spring.studentmanagementservice.studentmanagementservice.error.constraint.validation.Surname;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {

    @Id
    private Long id;

    @Name
    private String name;

    @Surname
    private String surname;

    @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?``{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

}