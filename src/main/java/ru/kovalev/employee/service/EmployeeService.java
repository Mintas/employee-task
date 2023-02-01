package ru.kovalev.employee.service;

import lombok.RequiredArgsConstructor;
import ru.kovalev.employee.exception.EmployeeNotFoundException;
import ru.kovalev.employee.hierarchy.EmployeeNamesInStructure;
import ru.kovalev.employee.hierarchy.Hierarchy.HierarchyNode;
import ru.kovalev.employee.model.Employee;
import ru.kovalev.employee.hierarchy.Hierarchy;

@RequiredArgsConstructor
public class EmployeeService {
    private final Hierarchy<Integer, Employee> employeeHierarchy;

    /**
     * Implementation depends on the fact that employeeHierarchy contains dummy-root-node aka BigBrother
     *
     * @return maximumDepth of actual hierarchy
     */
    public int maxDepth() {
        return employeeHierarchy.maxDepth() - 1;
    }

    public EmployeeNamesInStructure find(Integer employeeId) {
        HierarchyNode<Integer, Employee> employee = employeeHierarchy.findNode(employeeId);
        if (employee == null) {
            throw new EmployeeNotFoundException(employeeId);
        }
        HierarchyNode<Integer, Employee> supervisor = employeeHierarchy.findNode(employee.getParentId());
        return new EmployeeNamesInStructure(fullName(supervisor), fullName(employee),
                employee.getChildren().stream().map(this::fullName).toList());
    }

    private String fullName(HierarchyNode<Integer, Employee> employeeNode) {
        return employeeNode.getValue().getFullName();
    }
}
