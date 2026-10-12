package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService service;

    //@Autowired
    //private PetRepository repository;

//    public ResponseEntity<List<Pet>> listarTodosDisponiveis() {
//        List<Pet> pets = repository.findAll();
//        List<Pet> disponiveis = new ArrayList<>();
//        for (Pet pet : pets) {
//            if (pet.getAdotado() == false) {
//                disponiveis.add(pet);
//            }
//        }
//        return ResponseEntity.ok(disponiveis);
//    }

    @GetMapping
    public ResponseEntity<List<PetDto>> listarTodosDisponiveis() {
        List<PetDto> pets = service.buscarPetDisponivel();
        return ResponseEntity.ok(pets);
    }
}
