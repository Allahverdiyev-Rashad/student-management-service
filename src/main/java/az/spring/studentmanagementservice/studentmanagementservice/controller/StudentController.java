package az.spring.studentmanagementservice.studentmanagementservice.controller;

import az.spring.studentmanagementservice.studentmanagementservice.domain.Student;
import az.spring.studentmanagementservice.studentmanagementservice.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/{studentId}")
    public Student getOneStudent(@PathVariable Long studentId){
        return studentService.getStudentById(studentId);
    }

    @GetMapping()
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping()
    public Student createOneStudent(@RequestBody Student createStudent){
        return studentService.saveStudent(createStudent);
    }

    @PutMapping("/{studentId}")
    public Student updateOneStudent(@PathVariable Long studentId,@RequestBody Student updateStudent){
        return  studentService.updateStudent(studentId,updateStudent);
    }

    @DeleteMapping("/{studentId}")
    public void deleteOneStudent(@PathVariable Long studentId){
        studentService.deleteOneStudent(studentId);
    }

}