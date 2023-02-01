package ru.kovalev.employee.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kovalev.employee.BaseIntegrationTest;
import ru.kovalev.employee.hierarchy.EmployeeNamesInStructure;

class EmployeeServiceIntegrationTest extends BaseIntegrationTest {
    @Autowired
    EmployeeService employeeService;

    @Test
    void find() {
        assertEquals(6, employeeService.maxDepth());
        EmployeeNamesInStructure employee10Names = employeeService.find(10);
        assertEquals("EmployeeNamesInStructure[supervisorName=BIG_BROTHER, fullName=Darren Stanz, " +
                     "subordinateNames=[Ernest Staton, Rose Sims, Lauretta De Carlo, Mary Williams, Terri Burke, Audrey Osborn, Brian Binai, Concepcion Lozada]]",
                employee10Names.toString());
    }
}