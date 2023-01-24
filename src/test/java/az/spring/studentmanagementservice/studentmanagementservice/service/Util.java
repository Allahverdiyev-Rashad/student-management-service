package az.spring.studentmanagementservice.studentmanagementservice.service;

import az.spring.studentmanagementservice.studentmanagementservice.domain.Student;
import az.spring.studentmanagementservice.studentmanagementservice.domain.Teacher;
import az.spring.studentmanagementservice.studentmanagementservice.request.StudentRequest;
import az.spring.studentmanagementservice.studentmanagementservice.request.TeacherRequest;
import az.spring.studentmanagementservice.studentmanagementservice.response.StudentResponse;
import az.spring.studentmanagementservice.studentmanagementservice.response.TeacherResponse;
import lombok.Data;

@Data
public class Util {

    public static Student student() {
        Student student = new Student();
        student.setId(1L);
        student.setName("TestName");
        student.setSurname("TestSurname");
        student.setEmail("test@mailru");
        return student;
    }

    public static Student student2() {
        Student student2 = new Student();
        student2.setId(2L);
        student2.setName("TestName2");
        student2.setSurname("TestSurname2");
        student2.setEmail("test2@mailru");
        return student2;
    }

    public static StudentRequest studentRequest() {
        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setId(1L);
        studentRequest.setName("Kamil");
        studentRequest.setSurname("Ibadov");
        studentRequest.setEmail("kamil@mail.ru");
        return studentRequest;
    }

    public static StudentResponse studentResponse() {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(student().getId());
        studentResponse.setName(student().getName());
        studentResponse.setSurname(student().getSurname());
        studentResponse.setEmail(student().getEmail());
        return studentResponse;
    }

    public static StudentResponse updateStudentResponse() {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(1L);
        studentResponse.setName("TestName");
        studentResponse.setSurname("TestSurname");
        studentResponse.setEmail("test@mail.ru");
        return studentResponse;
    }

    public static Teacher teacher() {
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setUsername("TestUsurname");
        teacher.setPassword("testPassword");
        return teacher;
    }

    public static Teacher teacher2() {
        Teacher teacher2 = new Teacher();
        teacher2.setId(2L);
        teacher2.setUsername("TestUsurname2");
        teacher2.setPassword("testPassword2");
        return teacher2;
    }

    public static TeacherRequest teacherRequest() {
        TeacherRequest teacherRequest = new TeacherRequest();
        teacherRequest.setId(1L);
        teacherRequest.setUsername("testUsername");
        teacherRequest.setPassword("testPassword");
        return teacherRequest;
    }

    public static TeacherResponse teacherResponse() {
        TeacherResponse teacherResponse = new TeacherResponse();
        teacherResponse.setId(teacher().getId());
        teacherResponse.setUsername(teacher().getUsername());
        teacherResponse.setPassword(teacher().getPassword());
        return teacherResponse;
    }

    public static TeacherResponse updateTeacherResponce() {
        TeacherResponse teacherResponse = new TeacherResponse();
        teacherResponse.setId(1L);
        teacherResponse.setUsername("testUsurname");
        teacherResponse.setPassword("testPassword");
        return teacherResponse;
    }

}