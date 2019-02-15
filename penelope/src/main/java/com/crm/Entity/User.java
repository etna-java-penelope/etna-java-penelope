package com.crm.Entity;

import java.util.Map;

public class User {
    private int id;
    private String lastname;
    private String firstname;
    private String email;
    private String password;
    private String username;
    private String phone;
    private String address;
    private String city;
    private String zipcode;
    private String roles;
    private String status;

    public User() {
    }

    public User(Map<String, Object> data) {
        //for (Map.Entry<String, Object> entry : data.entrySet()) { System.out.println("Entry: " + entry.getKey() + " ---- " + entry.getValue());}
        if (data.get("id").toString().length() > 0)
            this.setId(Integer.parseInt(data.get("id").toString()));
        this.setLastname(data.get("lastname").toString());
        this.setFirstname(data.get("firstname").toString());
        this.setEmail(data.get("email").toString());
        this.setPassword(data.get("password").toString());
        this.setUsername(data.get("username").toString());
        this.setPhone(data.get("phone").toString());
        this.setAddress(data.get("address").toString());
        this.setCity(data.get("city").toString());
        this.setZipcode(data.get("zipcode").toString());
        this.setRoles(data.get("roles").toString());
        this.setStatus(data.get("status").toString());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
