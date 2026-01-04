package com.example.demoo.services;

import com.example.demoo.dto.JournalRequestDto;
import com.example.demoo.entities.jounralEntity;
import com.example.demoo.entities.userEntity;
import com.example.demoo.exceptions.InvalidIdException;
import com.example.demoo.exceptions.JwtAccessDeniedHandler;
import com.example.demoo.exceptions.UserNotFound;
import com.example.demoo.repository.jounralRepository;
import com.example.demoo.repository.userRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class jounralService {
    @Autowired
    jounralRepository jounralRepository;
    @Autowired
    userRepository userRepository;
    public List<jounralEntity> getAllJounrals(){
        String email = logInUserEmail();
        userEntity user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFound("User not found with email: " + email)
                );

        List<ObjectId> journalIds = user.getJournals();

        if (journalIds == null || journalIds.isEmpty()) {
            return Collections.emptyList();
        }

        return jounralRepository.findAllById(journalIds);
    }
    public jounralEntity getjounralById(String id){
        if (!ObjectId.isValid(id)) {
            throw new InvalidIdException("Invalid Jounral ID: " + id);
        }
        ObjectId objectId=new ObjectId(id);
        String email = logInUserEmail();
        userEntity user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFound("User not found with email: " + email)
                );

        List<ObjectId> journalIds = user.getJournals();
        if (journalIds == null || !journalIds.contains(objectId)) {
            throw new UserNotFound("Jounral not found with Id: " + id);
        }
            return jounralRepository.findById(objectId)
                    .orElseThrow(() ->
                            new UserNotFound("Jounral not found with Id: " + id));
    }

    public jounralEntity deleteJounral(String id){
        if (!ObjectId.isValid(id)) {
            throw new InvalidIdException("Invalid Jounral ID: " + id);
        }
        ObjectId objectId=new ObjectId(id);
        String email = logInUserEmail();
        userEntity user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFound("User not found with email: " + email)
                );

        ArrayList<ObjectId> journalIds = user.getJournals();
        if (journalIds == null || !journalIds.contains(objectId)) {
            throw new UserNotFound("Jounral not found with Id: " + id);
        }
        jounralEntity jounralEntity = jounralRepository.findById(objectId)
                .orElseThrow(() ->
                        new UserNotFound("Jounral not found with Id: " + id));
        journalIds.remove(objectId);
        user.setJournals(journalIds);
        userRepository.save(user);
        jounralRepository.deleteById(objectId);
        return jounralEntity;
    }
    public jounralEntity createJounral(jounralEntity newjounral){
        String email = logInUserEmail();
        userEntity user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFound("User not found with email: " + email)
                );
        jounralEntity save = jounralRepository.save(newjounral);
        user.getJournals().add(save.getId());
        userRepository.save(user);
        return save;
    }
    public jounralEntity updateJounral(jounralEntity newjounral){
            return jounralRepository.save(newjounral);
    }

    public String logInUserEmail(){
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }
}
