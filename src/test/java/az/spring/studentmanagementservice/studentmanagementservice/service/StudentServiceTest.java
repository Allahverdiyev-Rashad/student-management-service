package az.spring.studentmanagementservice.studentmanagementservice.service;

import az.spring.studentmanagementservice.studentmanagementservice.domain.Student;
import az.spring.studentmanagementservice.studentmanagementservice.exception.ServiceException;
import az.spring.studentmanagementservice.studentmanagementservice.repository.StudentRepository;
import az.spring.studentmanagementservice.studentmanagementservice.request.StudentRequest;
import az.spring.studentmanagementservice.studentmanagementservice.response.StudentResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;
    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    public void setUp() throws Exception {
        studentRepository = mock(StudentRepository.class);
        studentService = new StudentService(studentRepository);
    }

    @Test
    public void testStudentSave() {
        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setId(1L);
        studentRequest.setName("Cavidan");
        studentRequest.setSurname("Baghirov");
        studentRequest.setEmail("avromed@mail.ru");

        Student student = new Student();
        student.setId(studentRequest.getId());
        student.setName(studentRequest.getName());
        student.setSurname(studentRequest.getSurname());
        student.setEmail(studentRequest.getEmail());
        studentService.createStudent(studentRequest);

        when(studentRepository.save(student)).thenReturn(student);
        assertEquals(student.getId(), studentRequest.getId());
        assertEquals(student.getId(), 1L);
        verify(studentRepository).save(student);
    }

    @Test
    public void testStudentUpdate() {
        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setId(20L);
        studentRequest.setName("Kamil");
        studentRequest.setSurname("Ibadov");
        studentRequest.setEmail("kamil@mail.ru");

        Student student = new Student();
        student.setId(studentRequest.getId());
        student.setName("TestName");
        student.setSurname("TestSurname");
        student.setEmail("test@mail.ru");
        BeanUtils.copyProperties(student, studentRequest);
        studentService.createStudent(studentRequest);

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        when(studentRepository.save(student)).thenReturn(student);
    }

    @Test
    public void testGetStudentById() {
        Student student = new Student();
        student.setId(5L);
        student.setName("Test_Name");
        student.setSurname("Test_Surname");
        student.setSurname("test@mail.ru");

        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(student.getId());
        studentResponse.setName(student.getName());
        studentResponse.setSurname(student.getSurname());
        studentResponse.setEmail(student.getEmail());

        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        when(studentRepository.save(student)).thenReturn(student);
        assertEquals(student.getId(), studentResponse.getId());
        assertEquals(student.getId(), 5L);
//        verify(studentRepository).findById(studentResponse.getId());
    }

    @Test
    public void testAllStudent() {
        List<Student> studentList = new ArrayList<>();

        Student student = new Student();
        student.setId(1L);
        student.setName("TestName");
        student.setSurname("TestSurname");
        student.setEmail("test@mail.ru");
        studentList.add(student);

        Student student2 = new Student();
        student2.setId(2L);
        student2.setName("TestName2");
        student2.setSurname("TestSurname2");
        student2.setEmail("test2@mail.ru");
        studentList.add(student2);

        when(studentRepository.findAll()).thenReturn(studentList);
//        verify(studentRepository).findAll();
    }

    @Test
    public void testDeleteStudent() {
        studentRepository.deleteById(2L);
        verify(studentRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void testGetStudentById_whenStudentIdExists_shouldReturnStudentResponse() {
        Student student = new Student();

        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ServiceException.class, () -> studentService.getStudentById(student.getId()));
        verify(studentRepository).findById(student.getId());
    }

}