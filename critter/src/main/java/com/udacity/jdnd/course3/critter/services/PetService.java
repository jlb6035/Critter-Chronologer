package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepo;
import com.udacity.jdnd.course3.critter.repositories.PetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PetService {

    @Autowired
    PetRepo petRepo;

    @Autowired
    CustomerRepo customerRepo;

    public Pet savePet(Pet pet, Long customerId){
        Optional<Customer> customer = customerRepo.findById(customerId);
        pet.setCustomer(customer.get());
        petRepo.save(pet);
        customer.get().insertPet(pet);
        customerRepo.save(customer.get());
        return pet;
    }

    public Optional<Pet> getPet(Long petId){
        return petRepo.findById(petId);
    }

    public List<Pet> getPets(){
        return (List<Pet>) petRepo.findAll();
    }

    public List<Pet> getPetsByOwner(Long customerId){
        Optional<Customer> customer = customerRepo.findById(customerId);
        return customer.get().getPets();
    }

    public Optional<Customer> getOwner(Long customerId){
        return customerRepo.findById(customerId);
    }
}
