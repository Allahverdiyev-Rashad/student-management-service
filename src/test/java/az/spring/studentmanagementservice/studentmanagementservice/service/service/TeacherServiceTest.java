package az.spring.studentmanagementservice.studentmanagementservice.service.service;

import az.spring.studentmanagementservice.studentmanagementservice.domain.Teacher;
import az.spring.studentmanagementservice.studentmanagementservice.mappers.TeacherMapper;
import az.spring.studentmanagementservice.studentmanagementservice.mappers.TeacherMapperImpl;
import az.spring.studentmanagementservice.studentmanagementservice.repository.TeacherRepository;
import az.spring.studentmanagementservice.studentmanagementservice.response.TeacherResponse;
import az.spring.studentmanagementservice.studentmanagementservice.service.TeacherService;
import az.spring.studentmanagementservice.studentmanagementservice.service.util.Util;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @Spy
    private TeacherMapper teacherMapper = new TeacherMapperImpl();

    @InjectMocks
    private TeacherService teacherService;


    @Test
    public void testTeacherSave_whenCreateTeacher_shouldReturnTeacherRequest() {
        Teacher teacher = new Teacher();
        teacher.setId(Util.teacherRequest().getId());
        teacher.setUsername(Util.teacherRequest().getUsername());
        teacher.setPassword(Util.teacherRequest().getPassword());
        teacher.setStudentId(Util.teacherRequest().getStudentId());

        when(teacherRepository.save(teacher)).thenReturn(teacher);

        TeacherResponse result =
                teacherService.createTeacher(Util.teacherRequest());
        assertThat(result).isNotNull();
        assertEquals(teacher.getId(), Util.teacherRequest().getId());
        assertEquals(teacher.getUsername(), Util.teacherRequest().getUsername());

        verify(teacherRepository).save(teacher);
        verifyNoMoreInteractions(teacherRepository);
    }

    @Test
    public void testTeacherUpdate_whenUpdateTeacherById_shouldReturnUpdatedTeacherRequest() {
        when(teacherRepository.findById(Util.teacher().getId())).thenReturn(Optional.of(Util.teacher()));

        TeacherResponse result = teacherService.updateTeacher(Util.teacher().getId(), Util.teacherRequest());
        assertThat(result).isNotNull();
        assertEquals(Util.teacher().getId(), Util.teacherRequest().getId());
    }

    @Test
    public void testAllTeachers_whenGetAllTeachers_shouldReturnTeacherListResponse() {
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(Util.teacher());
        teacherList.add(Util.teacher2());

        when(teacherRepository.findAll()).thenReturn(teacherList);

        List<TeacherResponse> result = teacherService.getAllTeachers();
        assertThat(result).isNotNull();

        verify(teacherRepository).findAll();
        verifyNoMoreInteractions(teacherRepository);
    }

    @Test
    public void testTeacherById_whenGetTeacherById_shouldReturnTeacherResponse() {
        when(teacherRepository.findById(Util.teacher().getId())).thenReturn(Optional.of(Util.teacher()));

        TeacherResponse result = teacherService.getTeacherById(Util.teacher().getId());
        assertThat(result).isNotNull();
        assertEquals(Util.teacherResponse().getId(), (Util.teacher().getId()));
        assertThat(result.getId()).isEqualTo(1L);

        verify(teacherRepository).findById(Util.teacherResponse().getId());
        verifyNoMoreInteractions(teacherRepository);
    }

    @Test
    public void testDeleteTeacher() {
        teacherRepository.deleteById(2L);
        verify(teacherRepository, Mockito.times(1)).deleteById(2L);
        verifyNoMoreInteractions(teacherRepository);
    }

}