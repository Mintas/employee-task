package ru.kovalev.employee.exception;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(Integer id) {
        super("Employee not found in hierarchy by id = %d".formatted(id));
    }
}
