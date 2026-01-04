package com.example.demoo.controllers;

import com.example.demoo.dto.JournalRequestDto;
import com.example.demoo.entities.jounralEntity;
import com.example.demoo.services.jounralService;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/jounral")
public class jounralController {
   @Autowired
    private jounralService jounralService;

   @GetMapping("/all-jounrals")
    public ResponseEntity<List<jounralEntity>> getAllJournals(){
       List<jounralEntity> allJournals = jounralService.getAllJounrals();
       return ResponseEntity.status(HttpStatus.OK).body(allJournals);
   }
   @GetMapping("/{id}")
    public ResponseEntity<jounralEntity> getAllJournals(@PathVariable String id){
       return ResponseEntity.status(HttpStatus.OK).body(jounralService.getjounralById(id));
   }
   @PostMapping("/addNewJounral")
   public ResponseEntity<jounralEntity> createNewJournal( @RequestBody jounralEntity newJournal){
       jounralService.createJounral(newJournal);
       return  ResponseEntity.status(HttpStatus.CREATED).body(newJournal);
   }
    @DeleteMapping("/{id}")
    public ResponseEntity<jounralEntity> deleteJournal(@PathVariable String id){
        jounralEntity jounralEntity = jounralService.deleteJounral(id);
        return ResponseEntity.status(HttpStatus.OK).body(jounralEntity);
    }
    @PutMapping("/update")
    public ResponseEntity<jounralEntity> updateJournal(@RequestBody jounralEntity updatedJounral){
       return ResponseEntity.status(HttpStatus.OK).body(jounralService.updateJounral(updatedJounral));
//        Optional<jounralEntity> Journal = jounralService.getjounralById(id);
//        if(Journal.isPresent()){
//            jounralService.updateJounral(id);
//            return ResponseEntity.status(HttpStatus.OK).body(Journal.get());
//        }
//        else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
    }
}
