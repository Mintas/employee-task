package ru.kovalev.employee.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kovalev.employee.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
