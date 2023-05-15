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