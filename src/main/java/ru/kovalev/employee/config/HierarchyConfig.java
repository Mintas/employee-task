package ru.kovalev.employee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kovalev.employee.hierarchy.EmployeeHierarchyBuilder;
import ru.kovalev.employee.repository.EmployeeRepository;
import ru.kovalev.employee.service.EmployeeHierarchyFactory;
import ru.kovalev.employee.service.EmployeeService;

@Configuration
public class HierarchyConfig {

    @Bean
    public EmployeeHierarchyFactory employeeHierarchyFactory(EmployeeRepository employeeRepository,
                                                             EmployeeHierarchyBuilder employeeHierarchyBuilder) {
        return new EmployeeHierarchyFactory(0, employeeRepository, employeeHierarchyBuilder);
    }

    @Bean
    public EmployeeService employeeService(EmployeeHierarchyFactory employeeHierarchyFactory) {
        EmployeeService employeeService = new EmployeeService(employeeHierarchyFactory.buildHierarchy());
        System.out.println(employeeService.maxDepth());
        return employeeService;
    }
}
