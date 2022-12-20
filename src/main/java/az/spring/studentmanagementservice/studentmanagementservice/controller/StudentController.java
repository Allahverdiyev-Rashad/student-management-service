package az.spring.studentmanagementservice.studentmanagementservice.controller;

import az.spring.studentmanagementservice.studentmanagementservice.request.StudentRequest;
import az.spring.studentmanagementservice.studentmanagementservice.response.StudentListResponse;
import az.spring.studentmanagementservice.studentmanagementservice.response.StudentResponse;
import az.spring.studentmanagementservice.studentmanagementservice.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public StudentListResponse getAll() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{studentId}")
    public StudentResponse getStudent(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createStudent(@Valid @RequestBody StudentRequest studentRequest) {
        studentService.createStudent(studentRequest);
    }

    @PutMapping("/{studentId}")
    public void updateStudent(@PathVariable Long studentId, @Valid @RequestBody StudentRequest studentRequest) {
        studentService.updateStudent(studentId, studentRequest);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
    }

}