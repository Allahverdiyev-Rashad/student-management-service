package az.spring.studentmanagementservice.studentmanagementservice.service;

import az.spring.studentmanagementservice.studentmanagementservice.controller.StudentController;
import az.spring.studentmanagementservice.studentmanagementservice.request.StudentRequest;
import az.spring.studentmanagementservice.studentmanagementservice.response.StudentListResponse;
import az.spring.studentmanagementservice.studentmanagementservice.response.StudentResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@WebMvcTest(StudentController.class)
@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    public void testStudentSave_whenCreateStudent_shouldReturnStudentRequest() throws Exception {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(Util.studentRequest().getId());
        studentResponse.setName(Util.studentRequest().getName());
        studentResponse.setSurname(Util.studentRequest().getSurname());
        studentResponse.setEmail(Util.studentRequest().getEmail());

        when(studentService.createStudent(Util.studentRequest())).thenReturn(studentResponse);

        StudentResponse response = studentService.createStudent(Util.studentRequest());
        assertThat(response).isNotNull();
        assertEquals(studentResponse.getId(), Util.studentRequest().getId());
        assertEquals(studentResponse.getId(), 1L);

        verify(studentService).createStudent(Util.studentRequest());
        verifyNoMoreInteractions(studentService);

        String URI = "/students";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .param("Baghirov", Util.studentRequest().getSurname());
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testStudentUpdate_whenUpdateGetStudentById_shouldReturnUpdatedStudentRequest() throws Exception {
        when(studentService.updateStudent(1L, Util.studentRequest())).thenReturn(Util.updateStudentResponse());

        StudentResponse result = studentService.updateStudent(Util.studentRequest().getId(), Util.studentRequest());
        assertThat(result).isNotNull();
        assertEquals(Util.studentRequest().getId(), Util.updateStudentResponse().getId());

        String URI = "/student/1";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .param("TestSurname", Util.studentRequest().getSurname());
    }

    @Test
    public void testStudentById_whenGetStudentById_shouldReturnStudentResponse() throws Exception {
        when(studentService.getStudentById(Util.student().getId())).thenReturn(Util.studentResponse());

        StudentResponse result = studentService.getStudentById(1L);
        assertThat(result).isNotNull();
        assertEquals(Util.student().getId(), Util.updateStudentResponse().getId());
        assertEquals(result.getId(), 1L);

        verify(studentService).getStudentById(Util.studentResponse().getId());
        verifyNoMoreInteractions(studentService);

        String URI = "/students/1";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .param("TestName", Util.studentResponse().getName())
                .param("test@mail.ru", Util.studentResponse().getEmail());
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value("TestSurname"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("TestName"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteStudent() throws Exception {
        studentService.deleteStudent(2L);
        verify(studentService, times(1)).deleteStudent(2L);
        verifyNoMoreInteractions(studentService);

        String URI = "/students/2";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAllStudent_whenGetAllStudents_shouldReturnStudentListResponse() throws Exception {
        List<StudentRequest> list = Arrays.asList(
                new StudentRequest(1L, "Orxan", "Mustafa", "test@mail.ru"),
                new StudentRequest(2L, "Kamal", "Cavadov", "test2@mail.ru"),
                new StudentRequest(3L, "Davud", "Mamadov", "test3@mail.ru")
        );

        when(studentService.getAllStudents()).thenReturn((new StudentListResponse(list)));

        StudentListResponse resultList = studentService.getAllStudents();
        assertThat(resultList).isNotNull();
        assertThat(resultList.getStudents());

        verify(studentService).getAllStudents();
        verifyNoMoreInteractions(studentService);

        String URI = "/students";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}