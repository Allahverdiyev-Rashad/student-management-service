package az.spring.studentmanagementservice.studentmanagementservice.page;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class TeacherPage {

    private int pageNumber;
    private int pageSize;
    private Sort.Direction direction = Sort.Direction.ASC;
    private String sortBy = "username";

}