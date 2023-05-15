package az.spring.studentmanagementservice.studentmanagementservice.domain;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40)
    private String username;

    @Column(length = 25)
    private String password;
    
    private Long studentId;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    List<Student> studentList;

    @ManyToOne
    @JoinTable(name = "student_teacher", joinColumns = @JoinColumn(name = "teacher_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    @JoinColumn(name = "student_id", nullable = false)
    Student student;

}