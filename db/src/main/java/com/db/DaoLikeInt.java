package com.db;

import com.model.Like;

import java.util.List;

/**
 * Created by Konstantin on 30.08.2015.
 */
public interface DaoLikeInt {

    public List<Like> getListLikeByIdReciever(Long idReciever);

    public List<Like> getListLikeByIdReciever(Long idReciever, int timeLimit);

    public int getNumLikeByIdSenderToDay(Long idSender);

    public boolean setLike(Long idSender, Long idReciever, String comment, int typeBage);

    public boolean setLikeShowed(Long id);

    public boolean delLike(Long id);

    public Like getLikeById(Long id);

}
