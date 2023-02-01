package ru.kovalev.employee.hierarchy;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import ru.kovalev.employee.hierarchy.Hierarchy.HierarchyNode;
import ru.kovalev.employee.model.Employee;

class EmployeeHierarchyBuilderTest {
    EmployeeHierarchyBuilder builder = new EmployeeHierarchyBuilder();

    @Test
    void buildHierarchy_shouldSucceed_whenEmployeeTreeIsConsistent() {
        Employee michael3 = createEmp(3, 1, "Michael");
        int employeeRootId = 0;
        List<Employee> employeeList = List.of(
                createEmp(1, employeeRootId, "John Doe"),
                createEmp(2, 1, "Constantine Kish"),
                michael3,
                createEmp(4, 2, "Michael"),
                createEmp(5, 4, "Elloney Masked")
        );

        Hierarchy<Integer, Employee> hierarchy = builder.buildHierarchy(employeeList, employeeRootId);
        assertEquals(5, hierarchy.maxDepth());
        HierarchyNode<Integer, Employee> node3 = hierarchy.findNode(3);
        assertEquals(michael3, node3.getValue());
        assertEquals(1, node3.getParentId());
        assertTrue( node3.getChildren().isEmpty());

        HierarchyNode<Integer, Employee> bigBrother = hierarchy.findNode(employeeRootId);
        assertNull(bigBrother.getParentId());
        assertEquals(0, bigBrother.getValue().getEmployeeId());
        assertEquals(1, bigBrother.getChildren().size());
    }

    @Test
    void buildHierarchy_shouldSucceed_whenEmployeeTreeHasMultipleTopManagers() {
        int employeeRootId = 100;
        List<Employee> employeeList = List.of(
                createEmp(1, employeeRootId, "John Doe Senior"),
                createEmp(100_500, employeeRootId, "John Doe Junior")
        );

        Hierarchy<Integer, Employee> hierarchy = builder.buildHierarchy(employeeList, employeeRootId);
        assertEquals(2, hierarchy.maxDepth());

        HierarchyNode<Integer, Employee> bigBrother = hierarchy.findNode(employeeRootId);
        assertNull(bigBrother.getParentId());
        assertEquals(employeeRootId, bigBrother.getValue().getEmployeeId());
        assertEquals(2, bigBrother.getChildren().size());
    }


    private static Employee createEmp(Integer id, Integer supervisor, String name) {
        return new Employee(id, supervisor, name);
    }
}