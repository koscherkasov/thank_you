package com.db;

import com.model.Department;
import com.model.Role;
import com.model.User;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Konstantin on 30.08.2015.
 */
@Transactional
public class DaoUser implements DaoUserInt {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public User getUserByEmailPassword(String email, String password) {
        User user = (User) sessionFactory.getCurrentSession()
                .createQuery("from User u  where u.hashPass = :pass and u.email = :email")
                .setParameter("pass", password)
                .setParameter("email", email)
                .uniqueResult();
        return user;
    }
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        User user = (User) sessionFactory.getCurrentSession()
                .createQuery("from User u  where u.email = :email")
                .setParameter("email", email)
                .uniqueResult();
        return user;
    }

    @Transactional(readOnly = true)
    public Long getIdByEmailPassword(String email, String password) {
        User user = getUserByEmailPassword(email, password);
        if (user == null) return null;
        return user.getId();
    }


    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        User user = (User) sessionFactory.getCurrentSession()
                .createQuery("from User u  where u.id = :id")
                .setParameter("id", id)
                .uniqueResult();
        return user;
    }

    @Transactional(readOnly = true)
    public List<User> getListUser() {
        return sessionFactory.getCurrentSession()
                .createQuery("from User")
                .list();
    }

    @Transactional(readOnly = false)
    public boolean changePassword(Long id, String newHashPass) {
        sessionFactory.getCurrentSession()
                .createQuery("update User set hashPass = :hashPass where id=:id ")
                .setParameter("id", id)
                .setParameter("hashPass", newHashPass)
                .executeUpdate();
        return true;
    }

    @Transactional(readOnly = false)
    public void setUser(String ldap, String name, String surName, String email, String hashPass, Department dep, Role role) {

        User user = new User();
        user.setLdap(ldap);
        user.setName(name);
        user.setSurName(surName);
        user.setEmail(email);
        user.setHashPass(hashPass);
        user.setDep(dep);
        user.setRole(role);

        sessionFactory.getCurrentSession().save(dep);
        sessionFactory.getCurrentSession().save(role);
        sessionFactory.getCurrentSession().save(user);

    }
}
