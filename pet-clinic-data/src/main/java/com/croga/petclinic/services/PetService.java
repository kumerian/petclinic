package com.croga.petclinic.services;

import com.croga.petclinic.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findById(Long id);
    Pet save(Pet pet);
    Set<Pet> findAll();


}
