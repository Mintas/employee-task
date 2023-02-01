package ru.kovalev.employee.hierarchy;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import ru.kovalev.employee.hierarchy.Hierarchy.HierarchyNode;
import ru.kovalev.employee.model.Employee;

@Service
public class EmployeeHierarchyBuilder {
    public Hierarchy<Integer, Employee> buildHierarchy(Iterable<Employee> allEmployees, Integer bigBrotherId) {
        Map<Integer, HierarchyNode<Integer, Employee>> index = new HashMap<>();

        HierarchyNode<Integer, Employee> root = registerEmployee(bigBrother(bigBrotherId), index);
        registration(allEmployees, index);

        restoreRelation(allEmployees, index);
        return new Hierarchy<>(index, root);
    }

    private Employee bigBrother(int bigBrotherId) {
        return new Employee(bigBrotherId, null, "BIG_BROTHER");
    }

    private HierarchyNode<Integer, Employee> registerEmployee(
            Employee employee, Map<Integer, HierarchyNode<Integer, Employee>> index) {
        HierarchyNode<Integer, Employee> node = employeeNode(employee);
        index.put(node.getValue().getEmployeeId(), node);
        return node;
    }

    private HierarchyNode<Integer, Employee> employeeNode(Employee employee) {
        return new HierarchyNode<>(employee.getSupervisorId(), employee);
    }

    private void registration(Iterable<Employee> allEmployees,
                              Map<Integer, HierarchyNode<Integer, Employee>> index) {
        for (Employee employee : allEmployees) {
            registerEmployee(employee, index);
        }
    }

    private void restoreRelation(Iterable<Employee> allEmployees,
                                 Map<Integer, HierarchyNode<Integer, Employee>> index) {
        for (Employee employee : allEmployees) {
            HierarchyNode<Integer, Employee> supervisor = index.get(employee.getSupervisorId());
            supervisor.addChild(index.get(employee.getEmployeeId()));
        }
    }
}
