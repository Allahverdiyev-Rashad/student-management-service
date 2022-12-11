package az.spring.studentmanagementservice.studentmanagementservice.service;

import az.spring.studentmanagementservice.studentmanagementservice.domain.Student;
import az.spring.studentmanagementservice.studentmanagementservice.dto.request.StudentDto;
import az.spring.studentmanagementservice.studentmanagementservice.dto.response.StudentResponse;
import az.spring.studentmanagementservice.studentmanagementservice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentResponse getAllStudents() {

        List<StudentDto> studentDtoList = studentRepository.findAll()
                .stream().map(student -> domainToDto(student)).collect(Collectors.toList());

        return StudentResponse.builder()
                .students(studentDtoList)
                .build();

    }

    public StudentDto getStudentById(Long studentId) {

        return studentRepository.findById(studentId)
                .map(student -> domainToDto(student)).orElseThrow(() -> new RuntimeException());

    }

    public void createStudent(StudentDto studentDto) {

        Student student = new Student();
        BeanUtils.copyProperties(studentDto, student);
        studentRepository.save(student);

    }

    public void updateStudent(Long studentId , StudentDto studentDto){

        Student student = new Student();
        BeanUtils.copyProperties(studentDto,student);
        student.setId(studentId);
        studentRepository.save(student);

    }

    public void deleteStudent(Long studentId){

        studentRepository.deleteById(studentId);

    }

    private StudentDto domainToDto(Student student) {

        StudentDto studentDto = new StudentDto();
        BeanUtils.copyProperties(student, studentDto);
        return studentDto;

    }

}