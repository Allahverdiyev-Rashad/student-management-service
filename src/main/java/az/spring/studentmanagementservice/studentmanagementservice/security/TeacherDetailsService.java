package az.spring.studentmanagementservice.studentmanagementservice.security;

import az.spring.studentmanagementservice.studentmanagementservice.mappers.TeacherMapper;
import az.spring.studentmanagementservice.studentmanagementservice.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherDetailsService implements UserDetailsService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return teacherMapper.fromTeacherToTeacherDetails(teacherRepository.getTeacherByUsername(username).get());
    }

}
