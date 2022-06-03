package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepo extends CrudRepository<Schedule, Long> {
        List<Schedule> getAllByPetsContains(Pet pet);

        List<Schedule> getAllByEmployeesContains(Employee employee);

        List<Schedule> getAllByPetsIn(List<Pet> pets);

}
