/* Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
*/

package com.denksoft.springstarter.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;
import java.math.BigDecimal;

@Entity
public class BankAccountOperation extends AbstractSecureObject implements Serializable {

    public enum OperationType {
        debit, credit;
        public String getName() {
            return toString();
        }
    }

    @ManyToOne(optional = false)
    private BankAccount bankAccount = new BankAccount();

    private Timestamp issueTime;

    private OperationType type;

    private BigDecimal amount;

    public BankAccountOperation() {
        this.issueTime = new Timestamp(new Date().getTime());
    }

    public BankAccountOperation(BankAccount bankAccount, OperationType type, BigDecimal amount) {
        this();
        this.bankAccount = bankAccount;
        this.type = type;
        this.amount = amount;
    }

    public Timestamp getIssueTime() {
        return issueTime;
    }

    public OperationType[] getAvailableOperationTypes() {
        return OperationType.values();
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
