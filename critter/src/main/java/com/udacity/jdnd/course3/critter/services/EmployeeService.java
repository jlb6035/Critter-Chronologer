package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepo;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional
@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;


    public Employee save(Employee employee){
        return employeeRepo.save(employee);
    }

    public Optional<Employee> getEmployee(Long employeeId){
        return employeeRepo.findById(employeeId);
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId){
        Optional<Employee> employee = employeeRepo.findById(employeeId);
        employee.get().setDaysAvailable(daysAvailable);
        employeeRepo.save(employee.get());
    }

    public List<Employee> findEmployeesForService(Set<EmployeeSkill> skills, LocalDate date){
        List<Employee> employees = employeeRepo.getAllByDaysAvailableContains(date.getDayOfWeek());
        List<Employee> employeesWithSkills = new ArrayList<>();
        for (Employee employee : employees){
            if (employee.getSkills().containsAll(skills)){
                employeesWithSkills.add(employee);
            }
        }
        return employeesWithSkills;
    }
}
