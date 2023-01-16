package az.spring.studentmanagementservice.studentmanagementservice.service;

import az.spring.studentmanagementservice.studentmanagementservice.domain.Student;
import az.spring.studentmanagementservice.studentmanagementservice.exception.ServiceException;
import az.spring.studentmanagementservice.studentmanagementservice.repository.StudentRepository;
import az.spring.studentmanagementservice.studentmanagementservice.response.StudentListResponse;
import az.spring.studentmanagementservice.studentmanagementservice.response.StudentResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    public void testStudentSave_whenCreateStudent_shouldReturnStudentRequest() {
        Student student = new Student();
        student.setId(Util.studentRequest().getId());
        student.setName(Util.studentRequest().getName());
        student.setSurname(Util.studentRequest().getSurname());
        student.setEmail(Util.studentRequest().getEmail());

        when(studentRepository.save(student)).thenReturn(student);

        StudentResponse result = studentService.createStudent(Util.studentRequest());
        assertThat(result).isNotNull();
        assertEquals(student.getId(), Util.studentRequest().getId());
        assertEquals(student.getId(), 1L);

        verify(studentRepository).save(student);
        verifyNoMoreInteractions(studentRepository);
    }

    @Test
    public void testStudentById_whenGetStudentById_shouldReturnStudentResponse() {
        when(studentRepository.findById(Util.student().getId())).thenReturn(Optional.of(Util.student()));

        StudentResponse result = studentService.getStudentById(1L);
        assertThat(result).isNotNull();
        assertEquals(Util.student().getId(), Util.studentResponse().getId());
        assertEquals(result.getId(), 1L);

        verify(studentRepository).findById(Util.studentResponse().getId());
        verifyNoMoreInteractions(studentRepository);
    }

    @Test
    public void testAllStudent_whenGetAllStudents_shouldReturnStudentListResponse() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(Util.student());
        studentList.add(Util.student2());

        when(studentRepository.findAll()).thenReturn(studentList);

        StudentListResponse resultList = studentService.getAllStudents();
        assertThat(resultList).isNotNull();
        assertThat(resultList.getStudents());

        verify(studentRepository).findAll();
        verifyNoMoreInteractions(studentRepository);
    }

    @Test
    public void testStudentUpdate_whenUpdateGetStudentById_shouldReturnUpdatedStudentRequest() {
        when(studentRepository.findById(Util.student().getId())).thenReturn(Optional.of(Util.student()));

        StudentResponse result = studentService.updateStudent(Util.student().getId(), Util.studentRequest());
        assertThat(result).isNotNull();
        assertEquals(Util.studentRequest().getId(), Util.student().getId());
    }

    @Test
    public void testDeleteStudent() {
        studentRepository.deleteById(2L);
        verify(studentRepository, times(1)).deleteById(2L);
        verifyNoMoreInteractions(studentRepository);
    }

    @Test
    public void testHandleCustomException_whenStudentIdDoesNotExist_shouldThrowStudentNotFoundException() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ServiceException.class, () -> studentService.getStudentById(anyLong()));

        verify(studentRepository).findById(anyLong());
    }

}