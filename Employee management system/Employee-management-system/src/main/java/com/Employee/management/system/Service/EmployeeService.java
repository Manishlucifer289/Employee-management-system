package com.Employee.management.system.Service;

import com.Employee.management.system.Entity.Employee;
import com.Employee.management.system.Payload.Employeedto;

import java.util.List;

public interface EmployeeService {

    public Employeedto saveEmployee(Employeedto employeedto);
    public void deleteEmployee(long id);
    public List<Employeedto> fetchall();
    public Employee updateEmployee(Employee employee, long id);
}
