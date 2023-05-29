/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author admin
 */
public class Payment implements Serializable {

    private int id;
    private Date paymentDate;
    private float amount;
    private Contract contract;

    public Payment() {
        super();
    }
    public Payment(int id, Date paymentDate, float amount, Contract contract) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.contract = contract;
    }

    public int getId() {
        return id;
    }
    
    public Date getPaymentDate() {
        return paymentDate;
    }

    public float getAmount() {
        return amount;
    }

    public Contract getContract() {
        return contract;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
    
}
