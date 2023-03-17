package az.spring.studentmanagementservice.studentmanagementservice.service;

import az.spring.studentmanagementservice.studentmanagementservice.criteria.TeacherSearchCriteria;
import az.spring.studentmanagementservice.studentmanagementservice.domain.Teacher;
import az.spring.studentmanagementservice.studentmanagementservice.enums.ErrorCode;
import az.spring.studentmanagementservice.studentmanagementservice.error.ErrorMessage;
import az.spring.studentmanagementservice.studentmanagementservice.exception.ServiceException;
import az.spring.studentmanagementservice.studentmanagementservice.mappers.TeacherMapper;
import az.spring.studentmanagementservice.studentmanagementservice.page.TeacherPage;
import az.spring.studentmanagementservice.studentmanagementservice.repository.TeacherCriteriaRepository;
import az.spring.studentmanagementservice.studentmanagementservice.repository.TeacherRepository;
import az.spring.studentmanagementservice.studentmanagementservice.request.TeacherRequest;
import az.spring.studentmanagementservice.studentmanagementservice.response.TeacherResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final TeacherCriteriaRepository teacherCriteriaRepository;

    public Page<Teacher> getTeachersWithFilter(TeacherPage teacherPage, TeacherSearchCriteria searchCriteria) {
        log.info("Pages : {}", teacherPage);
        return teacherCriteriaRepository.findAllWithFilter(teacherPage, searchCriteria);
    }

    public List<TeacherResponse> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        log.info("TeacherListResponse : {}", teachers);
        return teacherMapper.fromModelToListResponse(teachers);
    }

    public TeacherResponse getTeacherById(Long teacherId) {
        log.info("teacherId : {}", teacherId);
        Teacher foundTeacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> ServiceException.of(ErrorCode.TEACHER_NOT_FOUND.name(), ErrorMessage.TEACHER_NOT_FOUND));
        log.info("teacherResponse : {}", foundTeacher);
        return teacherMapper.fromModelToResponse(foundTeacher);
    }

    public TeacherResponse createTeacher(TeacherRequest teacherRequest) {
        log.info("teacherRequest : {}", teacherRequest);
        Teacher teacher = teacherMapper.fromRequestToModel(teacherRequest);
        teacherRepository.save(teacher);
        log.info("teacherResponse : {}", teacher);
        return teacherMapper.fromModelToResponse(teacher);
    }

    public TeacherResponse updateTeacher(Long teacherId, TeacherRequest teacherRequest) {
        log.info("teacherRequest : {}", teacherRequest);
        Teacher foundTeacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> ServiceException.of(ErrorCode.TEACHER_NOT_FOUND.name(), ErrorMessage.TEACHER_NOT_FOUND));
        Teacher teacher = teacherMapper.fromRequestToModel(teacherRequest);
        teacher.setId(foundTeacher.getId());
        teacherRepository.save(teacher);
        log.info("teacherResponse : {}", teacher);
        return teacherMapper.fromModelToResponse(teacher);
    }

    public void deleteTeacher(Long teacherId) {
        teacherRepository.deleteById(teacherId);
    }

}