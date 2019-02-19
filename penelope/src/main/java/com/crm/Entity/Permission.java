package com.crm.Entity;

public class Permission {

    private Integer idRole;
    private String name;
    private String description;

    public Permission(){

    }

    public Permission(Integer idRole, String name, String description){
        this.idRole = idRole;
        this.name = name;
        this.description = description;
    }

    public Integer getIdRole(){
        return idRole;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public void setidRole(Integer idRole){
        this.idRole = idRole;
    }

    public void setname(String name){
        this.name = name;
    }

    public void setdescription(String description){
        this.description = description;
    }
}
