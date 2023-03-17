package az.spring.studentmanagementservice.studentmanagementservice.controller;

import az.spring.studentmanagementservice.studentmanagementservice.criteria.TeacherSearchCriteria;
import az.spring.studentmanagementservice.studentmanagementservice.domain.Teacher;
import az.spring.studentmanagementservice.studentmanagementservice.page.TeacherPage;
import az.spring.studentmanagementservice.studentmanagementservice.request.TeacherRequest;
import az.spring.studentmanagementservice.studentmanagementservice.response.TeacherResponse;
import az.spring.studentmanagementservice.studentmanagementservice.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/v2/pages")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<Teacher>> getAll(TeacherPage teacherPage, TeacherSearchCriteria searchCriteria) {
        return new ResponseEntity<>(teacherService.getTeachersWithFilter(teacherPage, searchCriteria), HttpStatus.OK);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TeacherResponse> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{teacherId}")
    @ResponseStatus(HttpStatus.OK)
    public TeacherResponse getTeacherById(@PathVariable Long teacherId) {
        return teacherService.getTeacherById(teacherId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TeacherResponse createTeacher(@RequestBody TeacherRequest teacherRequest) {
        return teacherService.createTeacher(teacherRequest);
    }

    @PutMapping("{teacherId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TeacherResponse updateTeacher(@PathVariable Long teacherId, @RequestBody TeacherRequest teacherRequest) {
        return teacherService.updateTeacher(teacherId, teacherRequest);
    }

    @DeleteMapping("{teacherId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTeacher(@PathVariable Long teacherId) {
        teacherService.deleteTeacher(teacherId);
    }

}