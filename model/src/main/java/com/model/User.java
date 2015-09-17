package com.model;

/**
 * Created by Konstantin on 14.08.2015.
 */
public class User {
    private Long id;
    private String ldap; //internal unique number in company
    private String name;
    private String surName;
    private String email;
    private String hashPass;
    private Department dep;
    private Role role;

    public User() {

    }

    public User(Long id, String ldap, String name, String surName, String email, String hashPass, Department dep, Role role) {
        this.id = id;
        this.ldap = ldap;
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.hashPass = hashPass;
        this.dep = dep;
        this.role = role;
    }



    public String getLdap() {
        return ldap;
    }

    public void setLdap(String ldap) {
        this.ldap = ldap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDep() {
        return dep;
    }

    public void setDep(Department dep) {
        this.dep = dep;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getHashPass() {
        return hashPass;
    }

    public void setHashPass(String hashPass) {
        this.hashPass = hashPass;
    }

}
