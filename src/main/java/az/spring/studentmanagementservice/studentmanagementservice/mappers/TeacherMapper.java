package az.spring.studentmanagementservice.studentmanagementservice.mappers;

import az.spring.studentmanagementservice.studentmanagementservice.domain.Teacher;
import az.spring.studentmanagementservice.studentmanagementservice.request.TeacherRegisterRequest;
import az.spring.studentmanagementservice.studentmanagementservice.request.TeacherRequest;
import az.spring.studentmanagementservice.studentmanagementservice.response.TeacherResponse;
import az.spring.studentmanagementservice.studentmanagementservice.security.TeacherDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TeacherMapper {

    TeacherResponse fromModelToResponse(Teacher teacher);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    Teacher fromRequestToModel(TeacherRequest teacherRequest);

    List<TeacherResponse> fromModelToListResponse(List<Teacher> teacherList);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    TeacherRequest fromModelToRequest(Teacher teacher);

    TeacherDetails fromTeacherToTeacherDetails(Teacher teacher);

    Teacher fromTeacherRegisterRequestToTeacher(TeacherRegisterRequest registerRequest);

}