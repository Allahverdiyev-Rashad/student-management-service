//package az.spring.studentmanagementservice.studentmanagementservice;
//
//import az.spring.studentmanagementservice.studentmanagementservice.domain.Student;
//import az.spring.studentmanagementservice.studentmanagementservice.service.repository.StudentRepositoryTest;
//import lombok.RequiredArgsConstructor;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.web.client.RestTemplate;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@RequiredArgsConstructor
//class StudentManagementServiceApplicationTest {
//
//    @LocalServerPort
//    private int port;
//
//    private String baseUrl = "http://localhost";
//
//    private static RestTemplate restTemplate;
//
//    private StudentRepositoryTest studentRepositoryTest;
//
//    @BeforeAll
//    public static void init() {
//        restTemplate = new RestTemplate();
//    }
//
//    @BeforeEach
//    public void setUp() {
//        baseUrl = baseUrl.concat(":").concat(port + "").concat("/students");
//    }
//
//    @Test
//    public void testAddStudent() {
//        Student student = new Student();
//        student.setName("IntegrationName");
//        student.setSurname("IntegrationSurname");
//        student.setEmail("IntegrationEmail");
//        student.setTeacherId(1L);
//
//        Student responseStudent = restTemplate.postForObject(baseUrl, student, Student.class);
//        assert responseStudent != null;
//        assertEquals("IntegrationName", responseStudent.getName());
//        assertEquals("IntegrationSurname", responseStudent.getSurname());
//    }
//
//}