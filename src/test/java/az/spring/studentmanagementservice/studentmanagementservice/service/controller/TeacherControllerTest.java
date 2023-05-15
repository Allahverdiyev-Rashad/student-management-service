package az.spring.studentmanagementservice.studentmanagementservice.service.controller;


import az.spring.studentmanagementservice.studentmanagementservice.controller.TeacherController;
import az.spring.studentmanagementservice.studentmanagementservice.response.TeacherResponse;
import az.spring.studentmanagementservice.studentmanagementservice.service.TeacherService;
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

import static az.spring.studentmanagementservice.studentmanagementservice.service.util.Util.teacher;
import static az.spring.studentmanagementservice.studentmanagementservice.service.util.Util.teacherRequest;
import static az.spring.studentmanagementservice.studentmanagementservice.service.util.Util.teacherResponse;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@WebMvcTest(TeacherController.class)
@ExtendWith(MockitoExtension.class)
public class TeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherService teacherService;

    @Test
    public void testAllTeachers_whenGetAllTeachers_shouldReturnTeacherListResponse() throws Exception {

        List<TeacherResponse> list = Arrays.asList(
                new TeacherResponse(1L, "Orxan", "Mustafa", 1L),
                new TeacherResponse(2L, "Kamal", "Cavadov", 2L),
                new TeacherResponse(3L, "Davud", "Mamadov", 1L)
        );

        when(teacherService.getAllTeachers()).thenReturn(list);

        List<TeacherResponse> resultList = teacherService.getAllTeachers();
        assertThat(resultList).isNotNull();

        verify(teacherService).getAllTeachers();
        verifyNoMoreInteractions(teacherService);

        String URI = "/api/v1/teachers";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testTeacherSave_whenCreateTeacher_shouldReturnTeacherRequest() throws Exception {
        TeacherResponse teacherResponse = new TeacherResponse();
        teacherResponse.setId(teacherRequest().getId());
        teacherResponse.setUsername(teacherRequest().getUsername());
        teacherResponse.setPassword(teacherRequest().getPassword());
        teacherResponse.setStudentId(teacherRequest().getStudentId());

        when(teacherService.createTeacher(teacherRequest())).thenReturn(teacherResponse);

        TeacherResponse response = teacherService.createTeacher(teacherRequest());
        assertThat(response).isNotNull();
        assertEquals(teacherResponse.getId(), teacherRequest().getId());
        assertEquals(teacherResponse.getId(), 1L);

        verify(teacherService).createTeacher(teacherRequest());
        verifyNoMoreInteractions(teacherService);

        String URI = "/api/v1/teachers";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .param("testUsername", teacherRequest().getUsername());
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testTeacherById_whenGetTeacherById_shouldReturnTeacherResponse() throws Exception {
        when(teacherService.getTeacherById(teacher().getId())).thenReturn(teacherResponse());

        TeacherResponse response = teacherService.getTeacherById(1L);
        assertThat(response).isNotNull();
        assertEquals(teacher().getPassword(), teacherResponse().getPassword());
        assertEquals(teacher().getId(), teacherResponse().getId());

        verify(teacherService).getTeacherById(teacherResponse().getId());
        verifyNoMoreInteractions(teacherService);

        String URI = "/api/v1/teachers/1";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .param("testPassword", teacherResponse().getPassword())
                .param("TestUsername", teacherResponse().getUsername());
        mockMvc.perform(requestBuilder);
    }

    @Test
    public void testUpdateTeacher_whenUpdateTeacherById_shouldReturnTeacherRequest() throws Exception {
        when(teacherService.updateTeacher(1L, teacherRequest())).thenReturn(teacherResponse());

        TeacherResponse response = teacherService.updateTeacher(teacherRequest().getId(), teacherRequest());
        assertThat(response).isNotNull();
        assertEquals(teacher().getId(), teacherRequest().getId());

        String URI = "/api/v1/teachers/1";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .param("TestUsername", teacherResponse().getUsername());
        mockMvc.perform(requestBuilder);
    }

    @Test
    public void testDeleteTeacher() throws Exception {
        teacherService.deleteTeacher(1L);
        verify(teacherService, times(1)).deleteTeacher(1L);
        verifyNoMoreInteractions(teacherService);

        String URI = "/api/v1/teachers/1";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}