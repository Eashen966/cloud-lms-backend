package com.lms.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(nullable = false, length = 100)
    private String name;

    // FIX: nullable = true — email is optional, not always sent from frontend
    @Column(nullable = true, unique = true, length = 100)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "matric_no", nullable = false, unique = true, length = 20)
    private String matricNo;

    @Column(nullable = false, length = 100)
    private String programme;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public enum Role {
        STUDENT, INSTRUCTOR
    }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getMatricNo() { return matricNo; }
    public void setMatricNo(String matricNo) { this.matricNo = matricNo; }

    public String getProgramme() { return programme; }
    public void setProgramme(String programme) { this.programme = programme; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}