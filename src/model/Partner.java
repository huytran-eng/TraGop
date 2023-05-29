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
public class Partner implements Serializable {

    private int id;
    private String name;
    private String address;
    private String email;
    private String phoneNo;
    private String description;

    public Partner() {
        super();
    }

    public Partner(int id, String name, String address, String email, String phoneNo, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNo = phoneNo;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Partner{" + "id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + ", phoneNo=" + phoneNo + ", description=" + description + '}';
    }

}
