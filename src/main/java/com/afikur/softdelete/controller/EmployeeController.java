package com.afikur.softdelete.controller;

import com.afikur.softdelete.model.Employee;
import com.afikur.softdelete.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @GetMapping
    public List<Employee> findAll(@RequestParam(value = "isDeleted", required = false, defaultValue = "false") boolean isDeleted) {
        return employeeService.findAll(isDeleted);
    }

    @DeleteMapping("/{id}")
    public void removeEmployee(@PathVariable("id") Long id) {
        employeeService.remove(id);
    }
}
