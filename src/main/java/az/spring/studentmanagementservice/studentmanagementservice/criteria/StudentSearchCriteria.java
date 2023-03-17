package az.spring.studentmanagementservice.studentmanagementservice.criteria;

import lombok.Data;

@Data
public class StudentSearchCriteria {

    private String name;
    private String surname;
    private String email;

}