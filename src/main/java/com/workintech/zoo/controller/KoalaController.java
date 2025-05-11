package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {
    Map<Integer, Koala> koalas;

    public KoalaController(){
        koalas = new HashMap<>();
    }

    @GetMapping
    public List<Koala> get(){
        return koalas.values().stream().toList();
    }
    @GetMapping("/{id}")
    public Koala get(@PathVariable int id){
        return koalas.get(id);
    }
    @PostMapping
    public ResponseEntity<Koala> post(@RequestBody Koala koala){
        koalas.put(koala.getId(),koala);
        return new ResponseEntity<>(koala, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public Koala put(@PathVariable int id,@RequestBody Koala koala){
        koalas.put(id,koala);
        return koala;
    }
    @DeleteMapping("/{id}")
    public Koala delete(@PathVariable int id){
        Koala koala = koalas.get(id);
        koalas.remove(id);
        return koala;
    }
}
