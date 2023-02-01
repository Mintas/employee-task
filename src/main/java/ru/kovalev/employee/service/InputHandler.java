package ru.kovalev.employee.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kovalev.employee.hierarchy.EmployeeNamesInStructure;

@Service
@RequiredArgsConstructor
public class InputHandler {
    private static final Pattern EMPLOYEE_ID = Pattern.compile("\\d+");
    private final EmployeeService employeeService;

    public boolean handeInput(String input) {
        Matcher matcher = EMPLOYEE_ID.matcher(input);
        if (matcher.matches()) {
            EmployeeNamesInStructure inStructure = employeeService.find(Integer.valueOf(input));
            System.out.println(inStructure);
            return true;
        }
        return false;
    }
}
