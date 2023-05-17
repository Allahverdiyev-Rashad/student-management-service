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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Data
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String name;

    @Column(length = 40)
    private String surname;

    @Column(length = 100)
    private String email;

    private Long teacherId;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    List<Teacher> teacherList;

    @ManyToOne
    @JoinTable(name = "student_teacher", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    @JoinColumn(name = "teacher_id", nullable = false)
    Teacher teacher;

}