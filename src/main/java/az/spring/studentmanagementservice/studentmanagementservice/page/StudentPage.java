package az.spring.studentmanagementservice.studentmanagementservice.page;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class StudentPage {

    private int pageNumber;
    private int pageSize;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortBy = "surname";

}