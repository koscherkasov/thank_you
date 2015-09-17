package com.db;

import com.model.Department;
import com.model.Role;
import com.model.User;

import java.util.List;

/**
 * Created by Konstantin on 30.08.2015.
 */
public interface DaoUserInt {
    public User getUserByEmailPassword(String email, String password);

    public User getUserByEmail(String email);

    public Long getIdByEmailPassword(String email, String password);

    public User getUserById(Long id);

    public List<User> getListUser();

    public boolean changePassword(Long id, String newHashPass);

    public void setUser(String ldap, String name, String surName, String email, String hashPass, Department dep, Role role);

}
