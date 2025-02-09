package edu.montana.csci.csci440.helpers;

import edu.montana.csci.csci440.model.Employee;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EmployeeHelper {
    public static String makeEmployeeTree() {
        // TODO, change this to use a single query operation to get all employees
        Employee employee = Employee.find(1); // root employee
        // and use this data structure to maintain reference information needed to build the tree structure
        Map<Long, List<Employee>> employeeMap = new HashMap<>();

        Map<Long, List<Employee>> titleMap = new HashMap<>();
        List<Employee> all = Employee.all();
        for (Employee emp : all) {
            Long title = emp.getReportsTo();
            List<Employee> employeesWithTitle = titleMap.get(title);
           if(employeesWithTitle == null){
               employeesWithTitle = new LinkedList<>();
               titleMap.put(title, employeesWithTitle);
           }
           employeesWithTitle.add(emp);
        }
        return "<ul>" + makeTree(employee, titleMap) + "</ul>";
    }

    // TODO - currently this method just usese the employee.getReports() function, which
    //  issues a query.  Change that to use the employeeMap variable instead
    public static String makeTree(Employee employee, Map<Long, List<Employee>> employeeMap) {
        String list = "<li><a href='/employees" + employee.getEmployeeId() + "'>"
                + employee.getEmail() + "</a><ul>";
        //List<Employee> reports = employee.getReports();
        List<Employee> reports = employeeMap.get(employee.getEmployeeId());
        if(reports == null){
            return list + "</ul></li>";
        }
        for (Employee report : reports) {
            list += makeTree(report, employeeMap);
        }
        return list + "</ul></li>";
    }
}
