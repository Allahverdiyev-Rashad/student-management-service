package az.spring.studentmanagementservice.studentmanagementservice.service;

import az.spring.studentmanagementservice.studentmanagementservice.domain.Student;
import az.spring.studentmanagementservice.studentmanagementservice.request.StudentRequest;
import az.spring.studentmanagementservice.studentmanagementservice.response.StudentListResponse;
import az.spring.studentmanagementservice.studentmanagementservice.repository.StudentRepository;
import az.spring.studentmanagementservice.studentmanagementservice.response.StudentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentListResponse getAllStudents() {
        List<StudentRequest> studentDtoList = studentRepository.findAll()
                .stream().map(this::domainToRequest).collect(Collectors.toList());
        return new StudentListResponse(studentDtoList);
    }

    public StudentResponse getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .map(this::domainToResponse).orElseThrow(RuntimeException::new);
    }

    public void createStudent(StudentRequest studentRequest) {
        Student student = new Student();
        BeanUtils.copyProperties(studentRequest, student);
        studentRepository.save(student);
    }

    public void updateStudent(Long studentId, StudentRequest studentRequest) {
        Student student = new Student();
        BeanUtils.copyProperties(studentRequest, student);
        student.setId(studentId);
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    private StudentRequest domainToRequest(Student student) {
        StudentRequest studentRequest = new StudentRequest();
        BeanUtils.copyProperties(student, studentRequest);
        return studentRequest;
    }

    private StudentResponse domainToResponse(Student student){
        StudentResponse studentResponse = new StudentResponse();
        BeanUtils.copyProperties(student,studentResponse);
        return studentResponse;
    }

}