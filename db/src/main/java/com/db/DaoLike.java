package com.db;

import com.model.Like;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Konstantin on 30.08.2015.
 */
@Transactional
public class DaoLike implements DaoLikeInt {

    private SessionFactory sessionFactory;

    private DaoUserInt daoUser;

    public void setDaoUser(DaoUserInt daoUser) {
        this.daoUser = daoUser;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Like> getListLikeByIdReciever(Long idReciever) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Like l  where l.reciever = :reciever order by date desc")
                .setParameter("reciever", daoUser.getUserById(idReciever))
                .list();
    }

    @Transactional(readOnly = true)
    public List<Like> getListLikeByIdReciever(Long idReciever, int timeLimit) {

        String query = "from Like l  where l.reciever = :reciever and l.date > :date order by date desc";
        Calendar c = new GregorianCalendar();
        c.setTime(new Date());

        switch (timeLimit) {
            case Calendar.DAY_OF_MONTH:
                c.add(Calendar.DAY_OF_YEAR, -1);
                Date date1 = c.getTime();
                return sessionFactory.getCurrentSession()
                        .createQuery(query)
                        .setParameter("reciever", daoUser.getUserById(idReciever))
                        .setParameter("date", date1)
                        .list();
            case Calendar.WEEK_OF_MONTH:
                c.add(Calendar.DAY_OF_YEAR, -7);
                Date date7 = c.getTime();
                return sessionFactory.getCurrentSession()
                        .createQuery(query)
                        .setParameter("reciever", daoUser.getUserById(idReciever))
                        .setParameter("date", date7)
                        .list();
            case Calendar.MONTH:
                c.add(Calendar.DAY_OF_YEAR, -30);
                Date date30 = c.getTime();
                return sessionFactory.getCurrentSession()
                        .createQuery(query)
                        .setParameter("reciever", daoUser.getUserById(idReciever))
                        .setParameter("date", date30)
                        .list();
            default:
                return getListLikeByIdReciever(idReciever);

        }

    }

    @Transactional(readOnly = true)
    public int getNumLikeByIdSenderToDay(Long idSender) {
        Long result = (Long) sessionFactory.getCurrentSession()
                .createQuery("select count(*) from Like l  where l.sender = :sender and l.date = :date")
                .setParameter("sender", daoUser.getUserById(idSender))
                .setParameter("date", new Date())
                .uniqueResult();
        return result.intValue();
    }

    @Transactional(readOnly = false)
    public boolean setLike(Long idSender, Long idReciever, String comment, int typeBage) {
        if (getNumLikeByIdSenderToDay(idSender) > 2) return false;

        Like like = new Like();
        like.setSender(daoUser.getUserById(idSender));
        like.setReciever(daoUser.getUserById(idReciever));
        like.setDate(new Date());
        like.setComment(comment);
        like.setTypeBage(typeBage);
        like.setIsShow(false);

        sessionFactory.getCurrentSession().save(like);
        return true;

    }

    @Transactional(readOnly = false)
    public boolean setLikeShowed(Long id) {
        sessionFactory.getCurrentSession()
                .createQuery("update Like set isShow = true where id=:id ")
                .setParameter("id", id)
                .executeUpdate();
        return true;
    }


    @Transactional(readOnly = false)
    public boolean delLike(Long id) {
        sessionFactory.getCurrentSession()
                .createQuery("delete from Like where id=:id ")
                .setParameter("id", id)
                .executeUpdate();
        return true;
    }
    @Transactional(readOnly = true)
    public Like getLikeById(Long id) {
        Like like = (Like) sessionFactory.getCurrentSession()
                .createQuery("from Like l  where l.id = :id")
                .setParameter("id", id)
                .uniqueResult();
        return like;
    }


}
