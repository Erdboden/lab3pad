package http.repositories;

import http.entities.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mark on 11/27/17.
 */
public class EmployeeRepository {

    private List<Employee> employees = new ArrayList<Employee>();

    private static final EmployeeRepository instance = new EmployeeRepository();

    private EmployeeRepository(){}

    public static EmployeeRepository getInstance(){
        return instance;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void pushEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public void popEmployee(Integer id) {
        for (Employee department:this.employees) {
            if(department.getId()==id){
                this.employees.remove(department);
            }
        }
    }
}
