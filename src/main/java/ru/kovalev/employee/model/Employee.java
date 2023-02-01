package ru.kovalev.employee.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class Employee {
    @Id
    private final Integer employeeId;
    private final Integer supervisorId;
    private final String fullName;
}
