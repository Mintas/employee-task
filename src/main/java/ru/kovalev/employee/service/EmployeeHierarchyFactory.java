package ru.kovalev.employee.service;

import lombok.RequiredArgsConstructor;
import ru.kovalev.employee.hierarchy.EmployeeHierarchyBuilder;
import ru.kovalev.employee.model.Employee;
import ru.kovalev.employee.hierarchy.Hierarchy;
import ru.kovalev.employee.repository.EmployeeRepository;

@RequiredArgsConstructor
public class EmployeeHierarchyFactory {
    private final Integer bigBrotherId;
    private final EmployeeRepository employeeRepository;
    private final EmployeeHierarchyBuilder employeeHierarchyBuilder;

    public Hierarchy<Integer, Employee> buildHierarchy() {
        Iterable<Employee> allEmployees = employeeRepository.findAll();

        return employeeHierarchyBuilder.buildHierarchy(allEmployees, bigBrotherId);
    }
}
