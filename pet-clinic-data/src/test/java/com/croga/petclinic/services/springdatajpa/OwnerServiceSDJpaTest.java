package com.croga.petclinic.services.springdatajpa;

import com.croga.petclinic.model.Owner;
import com.croga.petclinic.repositories.OwnerRepository;
import com.croga.petclinic.repositories.PetRepository;
import com.croga.petclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerServiceSDJpaTest {

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerServiceSDJpa service;

    final String LAST_NAME = "Smith";
    Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        //Mock
        when(ownerRepository.findByLastName((any()))).thenReturn(returnOwner);

        //Test
        Owner smith = service.findByLastName(LAST_NAME);
        assertEquals(LAST_NAME, smith.getLastName());
        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        //Mock
        Set<Owner> returnOwnersSet = new HashSet<>();
        returnOwnersSet.add(Owner.builder().id(1L).build());
        returnOwnersSet.add(Owner.builder().id(2L).build());
        when(ownerRepository.findAll()).thenReturn(returnOwnersSet);

        //Test
        Set<Owner> owners = service.findAll();
        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        //Mock
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        //Test
        Owner owner = service.findById(1L);
        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        //Mock
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        //Test
        Owner owner = service.findById(1L);
        assertNull(owner);
    }

    @Test
    void save() {
        //Mock
        when(ownerRepository.save(any())).thenReturn(returnOwner);

        //Test
        Owner ownerToSave = Owner.builder().id(1L).build();
        Owner savedOwner = service.save(ownerToSave);
        assertNotNull(savedOwner);
    }

    @Test
    void delete() {
        //Test
        service.delete(returnOwner);
        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        //Test
        service.deleteById(1L);
        verify(ownerRepository).deleteById(any());
    }
}