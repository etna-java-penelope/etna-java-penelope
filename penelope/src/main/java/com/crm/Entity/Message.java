package com.crm.Entity;

import java.util.Date;
import java.util.Map;

/**
 * Message
 */
public class Message {

    private Integer id;
    private Integer idUser;
    private String content;
    private String date;

    public Message() {

    }

    public Message(Integer idMessage, Integer idUser, String content, String date) {
        this.id = idMessage;
        this.idUser = idUser;
        this.content = content;
        this.date = date;

    }

    public Message(Map<String, Object> data)
    {
        if (data.get("id").toString().length() > 0)
            this.setId(Integer.parseInt(data.get("id").toString()));
        this.setContent(data.get("content").toString());
        this.setDate(data.get("date").toString());
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

    public String getDate() {
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

    public void setDate(String date) {
        this.date = date;
    }
}
