package az.spring.studentmanagementservice.studentmanagementservice.controller;

import az.spring.studentmanagementservice.studentmanagementservice.dto.request.StudentDto;
import az.spring.studentmanagementservice.studentmanagementservice.dto.response.StudentResponse;
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

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public StudentResponse getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{studentId}")
    public StudentDto getOneStudent(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createOneStudent(@RequestBody StudentDto studentDto) {
        studentService.createStudent(studentDto);
    }

    @PutMapping("/{studentId}")
    public void updateOneStudent(@PathVariable Long studentId , @RequestBody StudentDto studentDto){
        studentService.updateStudent(studentId,studentDto);
    }

    @DeleteMapping("/{studentId}")
    public void deleteOneStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
    }

}