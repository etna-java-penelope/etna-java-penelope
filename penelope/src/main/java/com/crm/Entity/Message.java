package com.crm.Entity;

import java.util.Date;

/**
 * Message
 */
public class Message {

    private Integer id;
    private Integer idUser;
    private String content;
    private Date date;

    public Message() {

    }

    public Message(Integer idMessage, Integer idUser, String content, Date date) {
        this.id = idMessage;
        this.idUser = idUser;
        this.content = content;
        this.date = date;

    }

    public Integer getId() {
        return id;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    public void setId(Integer idMessage) {
        this.id = idMessage;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
