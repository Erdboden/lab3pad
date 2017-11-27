package http.repositories;

import http.entities.Department;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mark on 11/27/17.
 */
public class DepartmentRepository {

    private List<Department> departments = new ArrayList<Department>();

    private static final DepartmentRepository instance = new DepartmentRepository();

    private DepartmentRepository(){}

    public DepartmentRepository getInstance(){
        return instance;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void pushDepartment(Department department) {
        this.departments.add(department);
    }

    public void popDepartment(Integer id) {
        for (Department department:this.departments) {
            if(department.getId()==id){
                this.departments.remove(department);
            }
        }
    }
}
