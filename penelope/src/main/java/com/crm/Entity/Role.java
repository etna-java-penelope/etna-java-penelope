package com.crm.Entity;

import java.util.Map;

public class Role {

    private Integer id;
    private String name;
    private String description;

    public Role(){

    }

    public Role(Integer idRole, String name, String description){
        this.id = idRole;
        this.name = name;
        this.description = description;
    }

    public Role(Map<String, Object> data)
    {
        if (data.get("id").toString().length() > 0)
            this.setId(Integer.parseInt(data.get("id").toString()));
        this.setName(data.get("name").toString());
        this.setDescription(data.get("description").toString());
    }

    public Integer getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public void setId(Integer idRole){
        this.id = idRole;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
