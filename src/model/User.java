/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author admin
 */
public class User implements Serializable {

    private int id;
    private String username;
    private String password;
    private String email;
    private String phoneNo;
    private String address;
    private String position;

    public User() {
        super();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public String getPosition() {
        return position;
    }

    public User(int id, String username, String password, String email, String phoneNo, String address, String position) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
        this.position = position;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", phoneNo=" + phoneNo + ", address=" + address + ", position=" + position + '}';
    }

}
