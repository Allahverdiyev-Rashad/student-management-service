package az.spring.studentmanagementservice.studentmanagementservice.response;

import az.spring.studentmanagementservice.studentmanagementservice.request.StudentRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class StudentListResponse {

    private List<StudentRequest> students;

}