package com.service;

import com.db.DaoLikeInt;
import com.db.DaoUserInt;
import com.model.Like;
import com.model.User;

import java.util.List;

/**
 * Created by Konstantin on 21.08.2015.
 */
public class Service implements ServiceInt {
    DaoUserInt daoUser = null;
    DaoLikeInt daoLike = null;

    public void setDaoUser(DaoUserInt daoUser) {
        this.daoUser = daoUser;
    }

    public void setDaoLike(DaoLikeInt daoLike) {
        this.daoLike = daoLike;
    }

    public Long checkPassword(String email, String password) {
        Long id = daoUser.getIdByEmailPassword(email, password);
        return id;
    }

    public User getUserById(Long id) {
        User user = daoUser.getUserById(id);
        return user;
    }

    public User getUserByEmail(String email) {
        User user = daoUser.getUserByEmail(email);
        return user;
    }

    public List<Like> getListLikeByIdReciever(Long idReciever) {
        List<Like> listLike = daoLike.getListLikeByIdReciever(idReciever);
        return listLike;
    }

    public List<Like> getListLikeByIdReciever(Long idReciever, int timeLimit) {
        List<Like> listLike = daoLike.getListLikeByIdReciever(idReciever, timeLimit);
        return listLike;
    }

    public List<User> getListUsers() {
        List<User> listUser = daoUser.getListUser();

        return listUser;
    }

    public int setLike(Long idSender, Long idReciever, String comment, int typeBage) {
        if (idSender.equals(idReciever)) return -1;
        boolean res= daoLike.setLike(idSender, idReciever, comment, typeBage);
        return res==true ? 0 : 1;
    }

    public boolean setLikeShowed(Long id) {
        return daoLike.setLikeShowed(id);
    }

    public boolean delLike(Long id) {

        return daoLike.delLike(id);
    }

    public boolean changePassword(Long id, String oldPassword, String newPassword) {
        User user = daoUser.getUserById(id);
        if (user == null) return false;
        user.getHashPass();
        //checkPassword
        String newHashPass = "123";
        return daoUser.changePassword(id, newHashPass);
    }

    public int getNumLikeByIdSenderToDay(Long idSender) {
        return daoLike.getNumLikeByIdSenderToDay(idSender);
    }

    public Like getLikeById(Long id) {
        return daoLike.getLikeById(id);
    }
}
