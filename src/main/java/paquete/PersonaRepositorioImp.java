package paquete;

import org.springframework.data.domain.*;
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
public class PersonaRepositorioImp {

    private final EntityManager entityManager;

    private final CriteriaBuilder criteriaBuilder;

    public PersonaRepositorioImp(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Persona> findAllFilter(PersonaPage personaPage, SearchCriteria searchCriteria) {

        CriteriaQuery<Persona> criteriaQuery = criteriaBuilder.createQuery(Persona.class);
        Root<Persona> personaRoot = criteriaQuery.from(Persona.class);
        Predicate predicate = getPredicate(searchCriteria, personaRoot);
        criteriaQuery.where(predicate);
        setOrder(personaPage, criteriaQuery, personaRoot);

        TypedQuery<Persona> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(personaPage.getPageNumber() * personaPage.getPageSize());
        typedQuery.setMaxResults(personaPage.getPageSize());

        Pageable pageable = getPageable(personaPage);

        Long personaCount = getPersonaCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, personaCount);

    }



    private Predicate getPredicate(SearchCriteria searchCriteria, Root<Persona> personaRoot) {

        List<Predicate> predicateList = new ArrayList<>();
        if (Objects.nonNull(searchCriteria.getName())){
            predicateList.add(
                    criteriaBuilder.like(personaRoot.get("name"),
                            "%" + searchCriteria.getName() + "%")
            );
        }
        if (Objects.nonNull(searchCriteria.getSurname())){
            predicateList.add(
                    criteriaBuilder.like(personaRoot.get("surname"),
                            "%" + searchCriteria.getSurname() + "%")
            );
        }
        if (Objects.nonNull(searchCriteria.getUsuario())){
            predicateList.add(
                    criteriaBuilder.like(personaRoot.get("usuario"),
                            "%" + searchCriteria.getUsuario() + "%")
            );
        }
        if (Objects.nonNull(searchCriteria.getCreated_date())){

            String condition=(String) searchCriteria.getCondition();
            switch (condition) {
                case "greater":
                predicateList.add(
                        criteriaBuilder.greaterThan(personaRoot.get("created_date"),
                                searchCriteria.getCreated_date()));
                break;

            case "less":
                predicateList.add(
                        criteriaBuilder.lessThan(personaRoot.get("created_date"),
                                searchCriteria.getCreated_date()));
                break;

            case "equal":
                predicateList.add(
                        criteriaBuilder.equal(personaRoot.get("created_date"),
                                searchCriteria.getCreated_date()));
                break;
            }
        }
        return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
    }

    private void setOrder(PersonaPage personaPage, CriteriaQuery<Persona> criteriaQuery, Root<Persona> personaRoot) {

        if(personaPage.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(personaRoot.get(personaPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(personaRoot.get(personaPage.getSortBy())));
        }
    }

    private Pageable getPageable(PersonaPage personaPage) {
        Sort sort = Sort.by(personaPage.getSortDirection(), personaPage.getSortBy());
        return PageRequest.of(personaPage.getPageNumber(), personaPage.getPageSize(), sort);
    }

    private long getPersonaCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Persona> countRoot = countQuery.from(Persona.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }


}
