package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/kangaroos")
@Slf4j
public class KangarooController {
    Map<Integer,Kangaroo> kangaroos;

    public KangarooController(){
        kangaroos = new HashMap<>();
    }

    @GetMapping
    public List<Kangaroo> get(){
        return kangaroos.values().stream().toList();
    }
    @GetMapping("/{id}")
    public Kangaroo get(@PathVariable int id){
        if(!kangaroos.containsKey(id)){
            ZooException e = new ZooException("invalid id for "+id, HttpStatus.NOT_FOUND);
            log.error(Logger.class.getName(),e);
            throw e;
        }

        return kangaroos.get(id);
    }
    @PostMapping()
    public ResponseEntity<Kangaroo> post(@RequestBody Kangaroo kangaroo){
        if(kangaroo.equals(new Kangaroo())){
            ZooException e = new ZooException("Null object",HttpStatus.BAD_REQUEST);
            log.error(Logger.class.getName(),e);
            throw e;
        }
        kangaroos.put(kangaroo.getId(),kangaroo);
        return new ResponseEntity<>(kangaroo,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public Kangaroo put(@PathVariable int id,@RequestBody Kangaroo kangaroo){
        kangaroos.put(id,kangaroo);
        return kangaroo;
    }
    @DeleteMapping("/{id}")
    public Kangaroo delete(@PathVariable int id){
        Kangaroo kangaroo = kangaroos.get(id);
        kangaroos.remove(id);
        return kangaroo;
    }
}
