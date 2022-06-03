package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.services.ScheduleService;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleService.createSchedule(scheduleDTO.getId(), scheduleDTO.getActivities(), scheduleDTO.getDate(), scheduleDTO.getEmployeeIds(), scheduleDTO.getPetIds());
        return convertEntityToDto(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();

        for(Schedule schedule : schedules){
            ScheduleDTO scheduleDTO = convertEntityToDto(schedule);
            scheduleDTOS.add(scheduleDTO);
        }

        return scheduleDTOS;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedules = scheduleService.getScheduleForPet(petId);

        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();

        for(Schedule schedule : schedules){
            ScheduleDTO scheduleDTO = convertEntityToDto(schedule);
            scheduleDTOS.add(scheduleDTO);
        }

        return scheduleDTOS;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = scheduleService.getScheduleForEmployee(employeeId);

        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();

        for(Schedule schedule : schedules){
            ScheduleDTO scheduleDTO = convertEntityToDto(schedule);
            scheduleDTOS.add(scheduleDTO);
        }

        return scheduleDTOS;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules = scheduleService.getScheduleForCustomer(customerId);

        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();

        for(Schedule schedule : schedules){
            ScheduleDTO scheduleDTO = convertEntityToDto(schedule);
            scheduleDTOS.add(scheduleDTO);
        }

        return scheduleDTOS;
    }

    public ScheduleDTO convertEntityToDto(Schedule schedule){
        List<Long> petIds = new ArrayList<>();
        List<Long> employeeIds = new ArrayList<>();
        for (Pet pet : schedule.getPets()){
            petIds.add(pet.getId());
        }

        for(Employee employee : schedule.getEmployees()){
            employeeIds.add(employee.getId());
        }

        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setActivities(schedule.getActivities());
        scheduleDTO.setDate(schedule.getDate());
        scheduleDTO.setPetIds(petIds);
        scheduleDTO.setEmployeeIds(employeeIds);
        return scheduleDTO;
    }
}
