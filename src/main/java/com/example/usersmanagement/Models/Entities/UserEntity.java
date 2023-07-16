package com.example.usersmanagement.Models.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.sql.Timestamp;
import java.util.Date;
@Entity
@Table(name = "users")
public class UserEntity {
    private long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String phoneNumber;
    private String email;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public UserEntity setId(long id) {
        this.id = id;
        return this;
    }



    @Column(name = "first_name",nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
    @Column(name = "last_name",nullable = false)
    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date", nullable = false)
    public Date getBirthDate() {
        return birthDate;
    }

    public UserEntity setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }
    @Column(name = "phone_number",nullable = false)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserEntity setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @Column( nullable = false,unique = true)
    @Pattern(regexp = "[A-Za-z._1-9]+@[a-z]+[.][a-z]+",message = "email must be valid!")
    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public UserEntity setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public UserEntity setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
