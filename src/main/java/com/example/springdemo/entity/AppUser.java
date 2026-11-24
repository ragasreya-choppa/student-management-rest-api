package com.example.springdemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="app_users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false,unique=true,length=50)
    private String username;

    @Column(name="password_hash",nullable=false)
    private String passwordHash;

    @Column(nullable=false, length=20)
    private String role;

    @Column(nullable=false)
    private boolean enabled = true;

    public AppUser(){

    }

    public AppUser(String username, String passwordHash, String role){
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.enabled = true;
    }

    public long getId(){
        return id;
    }

    public String getUsername(){
        return username;
    }

    public String getRole(){
        return role;
    }

    public String getPasswordHash(){
        return passwordHash;
    }

    public boolean isEnabled(){
        return enabled;
    }

    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }

}
