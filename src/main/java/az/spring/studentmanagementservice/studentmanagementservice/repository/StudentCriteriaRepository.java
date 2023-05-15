package az.spring.studentmanagementservice.studentmanagementservice.repository;

import az.spring.studentmanagementservice.studentmanagementservice.criteria.StudentSearchCriteria;
import az.spring.studentmanagementservice.studentmanagementservice.domain.Student;
import az.spring.studentmanagementservice.studentmanagementservice.page.StudentPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class StudentCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public StudentCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Student> findAllWithFilters(StudentPage studentPage, StudentSearchCriteria searchCriteria) {
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);
        Predicate predicate = getPredicate(searchCriteria, root);
        criteriaQuery.where(predicate);
        setOrder(studentPage, criteriaQuery, root);

        TypedQuery<Student> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(studentPage.getPageNumber() * studentPage.getPageSize());
        typedQuery.setMaxResults(studentPage.getPageSize());

        Pageable pageable = getPageable(studentPage);

        long studentCount = getStudentsCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, studentCount);
    }

    private Predicate getPredicate(StudentSearchCriteria searchCriteria, Root<Student> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(searchCriteria.getSurname())) {
            predicates.add(criteriaBuilder.like(root.get("surname"), "%" + searchCriteria.getSurname() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getName())) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + searchCriteria.getName() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getEmail())) {
            predicates.add(criteriaBuilder.equal(root.get("email"), searchCriteria.getEmail()));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(StudentPage studentPage, CriteriaQuery<Student> criteriaQuery, Root<Student> root) {
        if (studentPage.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(studentPage.getSortBy())));
        } else
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get(studentPage.getSortBy())));
    }

    private Pageable getPageable(StudentPage studentPage) {
        Sort sort = Sort.by(studentPage.getSortDirection(), studentPage.getSortBy());
        return PageRequest.of(studentPage.getPageNumber(), studentPage.getPageSize(), sort);
    }

    private long getStudentsCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Student> countRoot = countQuery.from(Student.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

}