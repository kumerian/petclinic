package com.croga.petclinic.services.springdatajpa;

import com.croga.petclinic.model.Vet;
import com.croga.petclinic.repositories.VetRepository;
import com.croga.petclinic.services.SpecialtyService;
import com.croga.petclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class VetServiceSDJpa implements VetService {

    private final VetRepository vetRepository;
    private final SpecialtyService specialtyService;

    public VetServiceSDJpa(VetRepository vetRepository,
                           SpecialtyService specialtyService) {
        this.vetRepository = vetRepository;
        this.specialtyService = specialtyService;
    }

    @Override
    public Set<Vet> findAll() {
        Set<Vet> vets = new HashSet<>();
        vetRepository.findAll().forEach(vets::add);
        return vets;
    }

    @Override
    public Vet findById(Long id) {
        return vetRepository.findById(id).orElse(null);
    }

    @Override
    public Vet save(Vet vet) {
        return vetRepository.save(vet);
    }

    @Override
    public void delete(Vet vet) {
        vetRepository.delete(vet);
    }

    @Override
    public void deleteById(Long id) {
        vetRepository.deleteById(id);
    }
}
