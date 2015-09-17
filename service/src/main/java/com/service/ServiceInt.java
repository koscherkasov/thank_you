package com.service;

import com.db.DaoLikeInt;
import com.db.DaoUserInt;
import com.model.Like;
import com.model.User;

import java.util.List;

/**
 * Created by Konstantin on 30.08.2015.
 */
public interface ServiceInt {

    public void setDaoUser(DaoUserInt daoUser);

    public void setDaoLike(DaoLikeInt daoLike);

    public Long checkPassword(String email, String password);

    public User getUserById(Long id);

    public User getUserByEmail(String email);

    public List<Like> getListLikeByIdReciever(Long idReciever);

    public List<Like> getListLikeByIdReciever(Long idReciever, int timeLimit);

    public List<User> getListUsers();

    public int setLike(Long idSender, Long idReciever, String comment, int typeBage);

    public boolean setLikeShowed(Long id);

    public boolean delLike(Long id);

    public boolean changePassword(Long id, String oldPassword, String newPassword);

    public int getNumLikeByIdSenderToDay(Long idSender);

    public Like getLikeById(Long id);
}
