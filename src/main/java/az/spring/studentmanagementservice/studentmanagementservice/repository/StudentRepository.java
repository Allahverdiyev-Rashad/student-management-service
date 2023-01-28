package az.spring.studentmanagementservice.studentmanagementservice.repository;

import az.spring.studentmanagementservice.studentmanagementservice.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}