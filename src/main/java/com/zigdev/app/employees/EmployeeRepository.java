package com.zigdev.app.employees;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
@ApplicationScoped
public class EmployeeRepository {
    @Inject
    EntityManager em;

    @Transactional(REQUIRED)
    public void create(Employee employee) {
        em.persist( employee );
    }

    @Transactional(REQUIRED)
    public Employee edit(Employee employee) {
        return em.merge( employee );
    }

    @Transactional(REQUIRED)
    public void remove(Employee employee) {
        em.remove( em.merge( employee ) );
    }

    public Employee find(Long id) {
        return em.find( Employee.class,id );
    }

    public List<Employee> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Employee.class));
        return em.createQuery(cq).getResultList();
    }



}
