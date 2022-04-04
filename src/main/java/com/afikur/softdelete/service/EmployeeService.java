package com.afikur.softdelete.service;

import com.afikur.softdelete.model.Employee;
import com.afikur.softdelete.repository.EmployeeRepository;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EntityManager entityManager;

    public EmployeeService(EmployeeRepository employeeRepository, EntityManager entityManager) {
        this.employeeRepository = employeeRepository;
        this.entityManager = entityManager;
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void remove(Long id){
        employeeRepository.deleteById(id);
    }

    public List<Employee> findAll(boolean isDeleted){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedEmployeeFilter");
        filter.setParameter("isDeleted", isDeleted);
        List<Employee> employees = employeeRepository.findAll();
        session.disableFilter("deletedEmployeeFilter");
        return employees;
    }
}
