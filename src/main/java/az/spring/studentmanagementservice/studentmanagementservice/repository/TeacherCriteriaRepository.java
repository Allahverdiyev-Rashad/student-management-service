package az.spring.studentmanagementservice.studentmanagementservice.repository;

import az.spring.studentmanagementservice.studentmanagementservice.criteria.TeacherSearchCriteria;
import az.spring.studentmanagementservice.studentmanagementservice.domain.Teacher;
import az.spring.studentmanagementservice.studentmanagementservice.page.TeacherPage;
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
public class TeacherCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public TeacherCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Teacher> findAllWithFilter(TeacherPage teacherPage, TeacherSearchCriteria searchCriteria) {
        CriteriaQuery<Teacher> criteriaQuery = criteriaBuilder.createQuery(Teacher.class);
        Root<Teacher> root = criteriaQuery.from(Teacher.class);
        Predicate predicate = getPredicate(searchCriteria, root);
        criteriaQuery.where(predicate);
        setOrder(teacherPage, criteriaQuery, root);

        TypedQuery<Teacher> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(teacherPage.getPageNumber() * teacherPage.getPageSize());
        typedQuery.setMaxResults(teacherPage.getPageSize());

        Pageable pageable = getPageable(teacherPage);

        long teacherCount = getTeacherCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, teacherCount);
    }

    private Predicate getPredicate(TeacherSearchCriteria searchCriteria, Root<Teacher> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(searchCriteria.getUsername())) {
            predicates.add(criteriaBuilder.like(root.get("username"), "%" + searchCriteria.getUsername() + "%"));
        }
        if (Objects.nonNull(searchCriteria.getPassword())) {
            predicates.add(criteriaBuilder.like(root.get("password"), "%" + searchCriteria.getPassword() + "%"));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(TeacherPage teacherPage, CriteriaQuery<Teacher> criteriaQuery, Root<Teacher> root) {
        if (teacherPage.getDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(teacherPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get(teacherPage.getSortBy())));
        }
    }

    private Pageable getPageable(TeacherPage teacherPage) {
        Sort sort = Sort.by(teacherPage.getDirection(), teacherPage.getSortBy());
        return PageRequest.of(teacherPage.getPageNumber(), teacherPage.getPageSize(), sort);
    }

    private long getTeacherCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Teacher> root = countQuery.from(Teacher.class);
        countQuery.select(criteriaBuilder.count(root)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }


}