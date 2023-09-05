package com.example.RegisterLogin.Service.impl;

import com.example.RegisterLogin.Dto.EmployeeDTO;
import com.example.RegisterLogin.Entity.Employee;
import com.example.RegisterLogin.Repo.EmployeeRepo;
import com.example.RegisterLogin.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeIMPL implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;


    @Override
    public String addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(

                employeeDTO.getEmployeeid(),
                employeeDTO.getEmployeename(),
                employeeDTO.getEmail(),
                employeeDTO.getPassword()
        );
        employeeRepo.save(employee);

        return employee.getEmployeename();
    }
}
