package az.spring.studentmanagementservice.studentmanagementservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherResponse {

    private Long id;
    private String username;
    private String password;
    private Long studentId;

}