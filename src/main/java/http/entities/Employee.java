package http.entities;

/**
 * Created by mark on 11/27/17.
 */
public class Employee {

    private Integer id;
    private String name;
    private String surename;
    private Integer departmentId;

    public Employee(Integer id, String name, String surename, Integer departmentId) {
        this.id = id;
        this.name = name;
        this.surename = surename;
        this.departmentId = departmentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", surename='" + surename + '\'' +
                ", departmentId=" + departmentId +
                '}';
    }
}
