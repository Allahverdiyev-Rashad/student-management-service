package az.spring.studentmanagementservice.studentmanagementservice.service.mapper;

import az.spring.studentmanagementservice.studentmanagementservice.domain.Teacher;
import az.spring.studentmanagementservice.studentmanagementservice.mappers.TeacherMapper;
import az.spring.studentmanagementservice.studentmanagementservice.request.TeacherRequest;
import az.spring.studentmanagementservice.studentmanagementservice.response.TeacherResponse;
import az.spring.studentmanagementservice.studentmanagementservice.service.util.Util;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TeacherMapTest {

    private TeacherMapper teacherMapper = Mappers.getMapper(TeacherMapper.class);

    @Test
    public void givenModelToRequest_whenMaps_thenCorrect() {
        Teacher teacher = new Teacher();
        teacher.setUsername(Util.teacher().getUsername());
        teacher.setPassword(Util.teacher().getPassword());

        TeacherRequest teacherRequest = teacherMapper.fromModelToRequest(teacher);
        assertEquals(teacher.getUsername(), teacherRequest.getUsername());
        assertEquals(teacher.getPassword(), teacherRequest.getPassword());
    }

    @Test
    public void givenRequestToModel_whenMaps_thenCorrect() {
        TeacherRequest teacherRequest = new TeacherRequest();
        teacherRequest.setUsername(Util.teacherRequest().getUsername());
        teacherRequest.setPassword(Util.teacherRequest().getPassword());

        Teacher teacher = teacherMapper.fromRequestToModel(teacherRequest);
        assertEquals(teacherRequest.getUsername(), teacher.getUsername());
        assertEquals(teacherRequest.getPassword(), teacher.getPassword());
    }

    @Test
    public void givenModelToResponse_whenMaps_thenCorrect() {
        Teacher teacher = new Teacher();
        teacher.setUsername(Util.teacher().getUsername());
        teacher.setPassword(Util.teacher().getPassword());

        TeacherResponse teacherResponse = teacherMapper.fromModelToResponse(teacher);
        assertEquals(teacher.getId(), teacherResponse.getId());
        assertEquals(teacher.getPassword(), teacherResponse.getPassword());
    }

    @Test
    public void givenModelToListResponse_whenMaps_thenCorrect() {
        Teacher teacher = new Teacher();
        teacher.setUsername(Util.teacher().getUsername());
        teacher.setPassword(Util.teacher().getPassword());

        Teacher teacher2 = new Teacher();
        teacher2.setUsername(Util.teacher2().getUsername());
        teacher2.setPassword(Util.teacher2().getPassword());

        List<Teacher> teachers = new ArrayList<>();
        teachers.add(teacher);
        teachers.add(teacher2);

        List<TeacherResponse> responseList = teacherMapper.fromModelToListResponse(teachers);
        assertNotNull(responseList);
        assertThat(responseList).isNotNull();
        assertThat(responseList.size());
    }

}