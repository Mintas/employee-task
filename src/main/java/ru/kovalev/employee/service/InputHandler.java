package ru.kovalev.employee.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kovalev.employee.exception.EmployeeNotFoundException;
import ru.kovalev.employee.hierarchy.EmployeeNamesInStructure;

@Service
@RequiredArgsConstructor
public class InputHandler {
    private static final Pattern EMPLOYEE_ID = Pattern.compile("\\d+");
    private final EmployeeService employeeService;

    public boolean handeInput(String input) {
        Matcher matcher = EMPLOYEE_ID.matcher(input);
        if (!matcher.matches()) {
            return false;
        }
        Integer employeeId = Integer.valueOf(input);
        System.out.printf("Attempting to find employee with id %s%n", employeeId);
        try {
            EmployeeNamesInStructure inStructure = employeeService.find(employeeId);
            System.out.println(inStructure);
        } catch (EmployeeNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return true;
    }
}
