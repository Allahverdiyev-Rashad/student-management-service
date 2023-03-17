package az.spring.studentmanagementservice.studentmanagementservice.service.repository;

import az.spring.studentmanagementservice.studentmanagementservice.domain.Student;
import az.spring.studentmanagementservice.studentmanagementservice.repository.StudentRepository;
import az.spring.studentmanagementservice.studentmanagementservice.service.util.Util;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testSaveStudent_whenSave_thenReturnSavedStudent() {
        studentRepository.save(Util.student());

        assertThat(Util.student()).isNotNull();
        assertThat(Util.student().getId()).isGreaterThan(0);
    }

    @Test
    public void testStudentList_whenFindAll_thenReturnStudentsList() {
        studentRepository.save(Util.student());
        studentRepository.save(Util.student2());

        List<Student> requestList = studentRepository.findAll();

        assertThat(requestList).isNotNull();
    }

    @Test
    public void testStudentById_whenFindById_thenReturnStudent() {
        studentRepository.save(Util.student());

        Optional<Student> foundStudent = studentRepository.findById(Util.student().getId());

        assertThat(foundStudent).isNotNull();
    }

    @Test
    public void testUpdateStudentById_whenUpdateStudent_thenReturnUpdatedStudent() {

        Student savedStudent = studentRepository.findById(1L).get();
        savedStudent.setName("UpdatedName");
        savedStudent.setSurname("UpdatedSurname");
        savedStudent.setEmail("UpdatedMail@mail.ru");
        Student updateStudent = studentRepository.save(savedStudent);

        assertThat(updateStudent).isNotNull();
        assertThat(updateStudent.getName()).isEqualTo("UpdatedName");
        assertThat(updateStudent.getEmail()).isEqualTo("UpdatedMail@mail.ru");
        assertThat(updateStudent.getId()).isEqualTo(savedStudent.getId());
    }

    @Test
    public void testDeleteStudent_whenDeleteStudent_thenRemoveStudent() {
        studentRepository.save(Util.student());
        studentRepository.deleteById(Util.student().getId());

        Optional<Student> optionalStudent = studentRepository.findById(Util.student().getId());

        assertThat(optionalStudent).isEmpty();
    }

}