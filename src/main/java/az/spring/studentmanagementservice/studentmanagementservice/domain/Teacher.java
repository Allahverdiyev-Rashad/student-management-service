package az.spring.studentmanagementservice.studentmanagementservice.domain;

import lombok.Data;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

    @Lob
    @Column(columnDefinition = "TEXT")//25 idi
    private String password;
    private Boolean isActive;
    
    private Long studentId;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    List<Student> studentList;

    @ManyToOne
    @JoinTable(name = "student_teacher", joinColumns = @JoinColumn(name = "teacher_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    @JoinColumn(name = "student_id", nullable = false)
    Student student;

}