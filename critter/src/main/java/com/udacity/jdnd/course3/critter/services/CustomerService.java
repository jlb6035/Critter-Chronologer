package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepo;
import com.udacity.jdnd.course3.critter.repositories.PetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    PetRepo petRepo;

    public Customer saveCustomer(@RequestBody Customer customer){
        return customerRepo.save(customer);
    }

    public List<Customer> getAllCustomers(){
        return (List<Customer>) customerRepo.findAll();
    }

    public Customer getCustomerByPet(Long petId){
        Optional<Pet> pet = petRepo.findById(petId);
        Customer customer = pet.get().getCustomer();
        return customer;
    }
}
