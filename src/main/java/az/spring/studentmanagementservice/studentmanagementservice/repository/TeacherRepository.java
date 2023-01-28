package az.spring.studentmanagementservice.studentmanagementservice.repository;

import az.spring.studentmanagementservice.studentmanagementservice.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}