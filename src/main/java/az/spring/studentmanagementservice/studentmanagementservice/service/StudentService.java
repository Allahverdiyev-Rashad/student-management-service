package az.spring.studentmanagementservice.studentmanagementservice.service;

import az.spring.studentmanagementservice.studentmanagementservice.domain.Student;
import az.spring.studentmanagementservice.studentmanagementservice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Student getStudentById(Long studentId){
        return studentRepository.findById(studentId).orElse(null);
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student saveStudent(Student createStudent){
        return studentRepository.save(createStudent);
    }

    public Student updateStudent(Long studentId,Student updateStudent){
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()){
            Student foundStudent = new Student();
            foundStudent.setName(updateStudent.getName());
            foundStudent.setSurname(updateStudent.getSurname());
            foundStudent.setEmail(updateStudent.getEmail());
            studentRepository.save(foundStudent);
            return foundStudent;
        }else
            return null;
    }

    public void deleteOneStudent(Long studentId){
        studentRepository.deleteById(studentId);
    }
}