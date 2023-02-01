package ru.kovalev.employee.hierarchy;

import java.util.List;

public record EmployeeNamesInStructure(String supervisorName, String fullName, List<String> subordinateNames) {
}
