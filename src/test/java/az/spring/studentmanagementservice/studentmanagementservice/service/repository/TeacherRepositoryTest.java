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
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void testTeacherSave_whenSaveTeacher_shouldReturnSavedTeacher() {
        teacherRepository.save(Util.teacher());
        Optional<Teacher> foundTeacher = teacherRepository.findById(Util.teacher().getId());

        assertThat(foundTeacher).isNotNull();
        assertThat(foundTeacher.get().getId()).isGreaterThan(0);
        assertEquals(foundTeacher.get().getId(), Util.teacher().getId());
        assertEquals(foundTeacher.get().getUsername(), Util.teacher().getUsername());
    }

    @Test
    public void testGetAllTeacherList_whenFindAll_shouldReturnTeachersList() {
        teacherRepository.save(Util.teacher());
        teacherRepository.save(Util.teacher2());

        List<Teacher> requestList = teacherRepository.findAll();

        assertThat(requestList).isNotNull();
        assertThat(requestList.size()).isEqualTo(4);
        assertEquals(requestList.get(1).getUsername(), Util.teacher2().getUsername());
        assertEquals(requestList.get(0).getUsername(), Util.teacher().getUsername());
        assertEquals(requestList.get(0).getId(), Util.teacher().getId());
    }

    @Test
    public void testGetTeacherById_whenFindById_shouldReturnTeacher() {
        teacherRepository.save(Util.teacher());

        Optional<Teacher> foundTeacher = teacherRepository.findById(Util.teacher().getId());

        assertThat(foundTeacher).isNotNull();
        assertEquals(foundTeacher.get().getId(), Util.teacher().getId());
        assertEquals(foundTeacher.get().getPassword(), Util.teacher().getPassword());
    }

    @Test
    public void testUpdateTeacherById_whenUpdateTeacher_shouldReturnUpdatedTeacher() {
        Teacher foundTeacher = teacherRepository.findById(Util.teacher().getId()).get();
        foundTeacher.setUsername("UpdatedUsername");
        foundTeacher.setPassword("UpdatedPassword");
        Teacher updatedTeacher = teacherRepository.save(foundTeacher);

        assertThat(updatedTeacher).isNotNull();
        assertThat(updatedTeacher.getId()).isEqualTo(foundTeacher.getId());
        assertEquals(foundTeacher.getUsername(), updatedTeacher.getUsername());
        assertEquals(foundTeacher.getPassword(), updatedTeacher.getPassword());
    }

    @Test
    public void testDeleteTeacher_whenDeleteTeacher_thenRemoveTeacher() {
        teacherRepository.save(Util.teacher());
        teacherRepository.deleteById(Util.teacher().getId());

        Optional<Teacher> foundTeacher = teacherRepository.findById(Util.teacher().getId());

        assertThat(foundTeacher).isEmpty();
    }

}