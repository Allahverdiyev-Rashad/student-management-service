package az.spring.studentmanagementservice.studentmanagementservice.service.repository;

import az.spring.studentmanagementservice.studentmanagementservice.domain.Teacher;
import az.spring.studentmanagementservice.studentmanagementservice.repository.TeacherRepository;
import az.spring.studentmanagementservice.studentmanagementservice.service.util.Util;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void testTeacherSave_whenSaveTeacher_shouldReturnSavedTeacher() {

        teacherRepository.save(Util.teacher());

        assertThat(Util.teacher()).isNotNull();
        assertThat(Util.teacher().getId()).isGreaterThan(0);

    }

    @Test
    public void testGetAllTeacherList_whenFindAll_shouldReturnTeachersList() {
        teacherRepository.save(Util.teacher());
        teacherRepository.save(Util.teacher2());

        List<Teacher> requestList = teacherRepository.findAll();

        assertThat(requestList).isNotNull();
    }

    @Test
    public void testGetTeacherById_whenFindById_shouldReturnTeacher() {
        teacherRepository.save(Util.teacher());

        Optional<Teacher> foundTeacher = teacherRepository.findById(Util.teacher().getId());

        assertThat(foundTeacher).isNotNull();
    }

    @Test
    public void testUpdateTeacherById_whenUpdateTeacher_shouldReturnUpdatedTeacher() {

        Teacher savedTeacher = teacherRepository.findById(Util.teacher().getId()).get();
        savedTeacher.setUsername("UpdatedUsername");
        savedTeacher.setPassword("UpdatedPassword");
        Teacher updateTeacher = teacherRepository.save(savedTeacher);

        assertThat(updateTeacher).isNotNull();
        assertThat(updateTeacher.getUsername()).isEqualTo(savedTeacher.getUsername());
        assertThat(updateTeacher.getPassword()).isEqualTo("UpdatedPassword");
        assertThat(updateTeacher.getUsername()).isEqualTo("UpdatedUsername");
    }

    @Test
    public void testDeleteTeacher_whenDeleteTeacher_thenRemoveTeacher() {
        teacherRepository.save(Util.teacher());
        teacherRepository.deleteById(Util.teacher().getId());

        Optional<Teacher> foundTeacher = teacherRepository.findById(Util.teacher().getId());

        assertThat(foundTeacher).isEmpty();
    }

}