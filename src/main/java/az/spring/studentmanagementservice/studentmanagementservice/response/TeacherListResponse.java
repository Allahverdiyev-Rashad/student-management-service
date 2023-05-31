package az.spring.studentmanagementservice.studentmanagementservice.response;

import az.spring.studentmanagementservice.studentmanagementservice.request.TeacherRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TeacherListResponse {

    private List<TeacherRequest> teachers;

}