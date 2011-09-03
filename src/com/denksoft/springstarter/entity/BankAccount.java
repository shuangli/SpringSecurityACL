/* Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
*/

package com.denksoft.springstarter.entity;


import javax.persistence.Entity;

import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.LinkedList;

@Entity
public class BankAccount extends AbstractSecureObject implements Serializable {

    private Date creationDate;

    private String bankAccountNo;

    private double balance;

    @ManyToOne
    private Customer customer = new Customer();

    @OneToMany(mappedBy = "bankAccount")
    private List<BankAccountOperation> operations = new LinkedList<BankAccountOperation>();

    public BankAccount() {
        this.creationDate = new Date();
    }

    public BankAccount(String bankAccountNo) {
        this();
        this.bankAccountNo = bankAccountNo;
    }

    public BankAccount(String bankAccountNo, double balance) {
        this(bankAccountNo);
        this.balance = balance;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<BankAccountOperation> getOperations() {
        return operations;
    }

    public void setOperations(List<BankAccountOperation> operations) {
        this.operations = operations;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

   /* public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }*/
}