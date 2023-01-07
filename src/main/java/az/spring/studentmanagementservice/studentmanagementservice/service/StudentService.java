package az.spring.studentmanagementservice.studentmanagementservice.service;

import az.spring.studentmanagementservice.studentmanagementservice.domain.Student;
import az.spring.studentmanagementservice.studentmanagementservice.enums.ErrorCode;
import az.spring.studentmanagementservice.studentmanagementservice.error.ErrorMessage;
import az.spring.studentmanagementservice.studentmanagementservice.exception.ServiceException;
import az.spring.studentmanagementservice.studentmanagementservice.repository.StudentRepository;
import az.spring.studentmanagementservice.studentmanagementservice.request.StudentRequest;
import az.spring.studentmanagementservice.studentmanagementservice.response.StudentListResponse;
import az.spring.studentmanagementservice.studentmanagementservice.response.StudentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentListResponse getAllStudents() {
        List<StudentRequest> studentDtoList = studentRepository.findAll()
                .stream().map(this::domainToRequest).collect(Collectors.toList());
        log.info("StudentListResponse : {}", studentDtoList);
        return new StudentListResponse(studentDtoList);
    }

    public StudentResponse getStudentById(Long studentId) {
        log.info("studentId : {}", studentId);
        Student foundStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> ServiceException.of(ErrorCode.STUDENT_NOT_FOUND.name(), ErrorMessage.STUDENT_NOT_FOUND));
        StudentResponse studentResponse = new StudentResponse();
        BeanUtils.copyProperties(foundStudent, studentResponse);
        log.info("studentResponse : {}", studentResponse);
        return studentResponse;
    }

    public StudentResponse createStudent(StudentRequest studentRequest) {
        log.info("studentRequest : {}", studentRequest);
        Student student = new Student();
        BeanUtils.copyProperties(studentRequest, student);
        studentRepository.save(student);

        StudentResponse studentResponse = new StudentResponse();
        BeanUtils.copyProperties(studentRequest, studentResponse);
        log.info("studentResponse : {}", studentResponse);
        return studentResponse;
    }

    public StudentResponse updateStudent(Long studentId, StudentRequest studentRequest) {
        log.info("studentRequest : {}", studentRequest);
        Student foundStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> ServiceException.of(ErrorCode.STUDENT_NOT_FOUND.name(), ErrorMessage.STUDENT_NOT_FOUND));
        Student student = new Student();
        BeanUtils.copyProperties(studentRequest, student);
        studentRepository.save(student);

        StudentResponse studentResponse = new StudentResponse();
        BeanUtils.copyProperties(studentRequest, studentResponse);
        log.info("studentResponse : {}", studentResponse);
        return studentResponse;
    }

    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
        log.info("studentId : {}", studentId);
    }

    private StudentRequest domainToRequest(Student student) {
        log.info("student : {}", student);
        StudentRequest studentRequest = new StudentRequest();
        BeanUtils.copyProperties(student, studentRequest);
        log.info("studentRequest : {}", studentRequest);
        return studentRequest;
    }

    private StudentResponse domainToResponse(Student student) {
        log.info("student : {}", student);
        StudentResponse studentResponse = new StudentResponse();
        BeanUtils.copyProperties(student, studentResponse);
        log.info("studentResponse : {}", studentResponse);
        return studentResponse;
    }

    private StudentResponse requestToResponse(StudentRequest studentRequest) {
        log.info("studentRequest : {}", studentRequest);
        StudentResponse studentResponse = new StudentResponse();
        BeanUtils.copyProperties(studentRequest, studentResponse);
        log.info("studentResponse : {}", studentResponse);
        return studentResponse;
    }

}