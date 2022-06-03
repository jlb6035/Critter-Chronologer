package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepo;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepo;
import com.udacity.jdnd.course3.critter.repositories.PetRepo;
import com.udacity.jdnd.course3.critter.repositories.ScheduleRepo;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional
@Service
public class ScheduleService {

    @Autowired
    ScheduleRepo scheduleRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    PetRepo petRepo;

    public Schedule createSchedule(Long id, Set<EmployeeSkill> activites, LocalDate date, List<Long> employeeIds, List<Long> petIds){
        List<Pet> pets = (List<Pet>) petRepo.findAllById(petIds);
        List<Employee> employees = (List<Employee>) employeeRepo.findAllById(employeeIds);
        Schedule schedule = new Schedule();
        schedule.setDate(date);
        schedule.setEmployees(employees);
        schedule.setPets(pets);
        schedule.setActivities(activites);
        return scheduleRepo.save(schedule);
    }

    public List<Schedule> getScheduleForPet(Long petId){
        Optional<Pet> pet = petRepo.findById(petId);
        List<Schedule> schedules = scheduleRepo.getAllByPetsContains(pet.get());
        return schedules;
    }

    public List<Schedule> getScheduleForEmployee(Long employeeId){
        Optional<Employee> employee = employeeRepo.findById(employeeId);
        List<Schedule> schedules = scheduleRepo.getAllByEmployeesContains(employee.get());
        return schedules;
    }

    public List<Schedule> getScheduleForCustomer(Long customerId){
        Optional<Customer> customer = customerRepo.findById(customerId);
        List<Schedule> schedules = scheduleRepo.getAllByPetsIn(customer.get().getPets());
        return schedules;
    }

    public List<Schedule> getAllSchedules(){
        return (List<Schedule>) scheduleRepo.findAll();
    }
}
