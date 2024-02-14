package com.example.course.controller;

import com.example.course.CatDTO;
import com.example.course.entity.Cat;
import com.example.course.repository.catRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Tag(name = "main_methods")
@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {

    private  final catRepo catRepo;

    @Operation(
            summary = "Вставляем новые данные о котике в базу",
            description = "Получает DTO кота и билдером собирает и сохраняет сущность в базу"

    )
    @PostMapping("/api/add")
    public void addCat(@RequestBody CatDTO catDTO){

        log.info("New row :" + catRepo.save(Cat.builder().
                age(catDTO.getAge()).
                weight(catDTO.getWeight()).
                name(catDTO.getName())
                .build()))
        ;

    }
    @Operation(
            summary ="Получение данных о котиках",
            description = "Получение детальной информации из бд о котиках"
    )
    @SneakyThrows
    @GetMapping("/api/all")
    public List<Cat> getCat(){
        return catRepo.findAll();
    }

    @Operation(
            summary ="Получение данных о котиках",
            description = "Получение детальной информации из бд о за определеным ключем"
    )
    @GetMapping("/api")
    public Cat getCat(@RequestParam int id){
        return catRepo.findById(id).orElseThrow();
    }

    @Operation(
            summary ="Удаление котика",
            description = "Удаления котика из бд за его ключем"
    )
    @DeleteMapping("/api")
    public void deleteCat(@RequestParam int id){
        catRepo.deleteById(id);
    }

    @Operation(
            summary ="Изменения информации",
            description = "Изменения информации о котиках в бд"
    )
    @PutMapping("/api")
    public String changeCat(@RequestBody Cat cat){
        if(!catRepo.existsById(cat.getId())){
            return "No such row";
        }
      return catRepo.save(cat).toString();
    }



}
