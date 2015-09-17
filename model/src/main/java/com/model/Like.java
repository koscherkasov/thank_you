package com.model; /**
 * Created by Konstantin on 14.08.2015.
 */
import java.util.Date;

public class Like {
    private Long id;
    private User sender;
    private User reciever;
    private Date date;
    private String comment;
    private int typeBage;
    private boolean isShow; //flag - for notificaton

    public Like() {
    }

    public Like(Long id, User sender, User reciever, Date date, String comment, int typeBage, boolean isShow) {
        this.id = id;
        this.sender = sender;
        this.reciever = reciever;
        this.date = date;
        this.comment = comment;
        this.typeBage = typeBage;
        this.isShow = isShow;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReciever() {
        return reciever;
    }

    public void setReciever(User reciever) {
        this.reciever = reciever;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getTypeBage() {
        return typeBage;
    }

    public void setTypeBage(int typeBage) {
        this.typeBage = typeBage;
    }

    public boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(boolean isShow) {
        this.isShow = isShow;
    }
}
