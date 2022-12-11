package az.spring.studentmanagementservice.studentmanagementservice.dto.response;

import az.spring.studentmanagementservice.studentmanagementservice.dto.request.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {

    private List<StudentDto> students;

}