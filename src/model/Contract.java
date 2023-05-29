/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Contract implements Serializable {

    private int id;
    private Date createdDate;
    private float productsPrice;
    private float interestRate;
    private float totalPrice;
    private int duration;
    private int payCycle;
    private User user;
    private Customer customer;
    private Partner partner;
    private ArrayList<ContractProduct> contractProducts;
    private ArrayList<Payment> payments;

    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public Contract() {
        super();
    }

    public Contract(int id, Date createdDate, float productsPrice, float interestRate, float totalPrice, int duration, int payCycle, User user, Customer customer, Partner partner, ArrayList<ContractProduct> contractProducts) {
        this.id = id;
        this.createdDate = createdDate;
        this.productsPrice = productsPrice;
        this.interestRate = interestRate;
        this.totalPrice = totalPrice;
        this.duration = duration;
        this.payCycle = payCycle;
        this.user = user;
        this.customer = customer;
        this.partner = partner;
        this.contractProducts = contractProducts;
    }

    public void setPayments(ArrayList<Payment> payments) {
        this.payments = payments;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setProductsPrice(float productsPrice) {
        this.productsPrice = productsPrice;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setPayCycle(int payCycle) {
        this.payCycle = payCycle;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public void setContractProducts(ArrayList<ContractProduct> contractProducts) {
        this.contractProducts = contractProducts;
    }

    public int getId() {
        return id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public float getProductsPrice() {
        return productsPrice;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Contract{" + "id=" + id + ", createdDate=" + createdDate + ", productsPrice=" + productsPrice + ", interestRate=" + interestRate + ", totalPrice=" + totalPrice + ", duration=" + duration + ", payCycle=" + payCycle + ", user=" + user + ", customer=" + customer + ", partner=" + partner + ", contractProducts=" + contractProducts + '}';
    }

    public int getDuration() {
        return duration;
    }

    public int getPayCycle() {
        return payCycle;
    }

    public User getUser() {
        return user;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Partner getPartner() {
        return partner;
    }

    public ArrayList<ContractProduct> getContractProducts() {
        return contractProducts;
    }

    public void addContractProduct(ContractProduct contractProduct) {
        this.contractProducts.add(contractProduct);
    }
}
