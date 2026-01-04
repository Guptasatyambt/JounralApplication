package com.example.demoo.entities;

import com.mongodb.DBRef;
import com.mongodb.lang.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
@Document(collection = "user")
public class userEntity {
    @Id

    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String email;
    private String userName;
    private String password;
    private String role;
    private ArrayList<ObjectId> journals=new ArrayList<>();

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<ObjectId> getJournals() {
        return journals;
    }

    public void setJournals(ArrayList<ObjectId> journals) {
        this.journals = journals;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
