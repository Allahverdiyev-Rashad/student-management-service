package az.spring.studentmanagementservice.studentmanagementservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    @Id
    private long id;

    private String name;

    private String surname;

    private String email;

}