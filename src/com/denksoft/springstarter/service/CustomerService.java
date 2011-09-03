/* Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
*/

package com.denksoft.springstarter.service;

import com.denksoft.springstarter.entity.Customer;
import com.denksoft.springstarter.entity.BankAccount;
import com.denksoft.springstarter.entity.BankAccountOperation;
import org.springframework.security.annotation.Secured;

import java.util.Map;
import java.util.Collection;
import java.util.List;


public interface CustomerService {

    public Customer setupCustomer(Customer customer);

    @Secured({"ROLE_CUSTOMER","AFTER_ACL_READ"})
    public Customer getCustomer(long id);

    @Secured({"ROLE_CUSTOMER","AFTER_ACL_READ"})
    public BankAccount getBankAccount(long id);
    
    @Secured({"ROLE_CUSTOMER","AFTER_ACL_READ"})
    BankAccountOperation getBankAccountOperation(long id);

    @Secured({"ROLE_CUSTOMER","AFTER_ACL_COLLECTION_READ"})
    public Collection<BankAccount> getCustomerBankAccounts();

    @Secured({"ROLE_CUSTOMER","AFTER_ACL_COLLECTION_READ"})
    public Collection<BankAccountOperation> getCustomerBankAccountOperations();

    public Collection<BankAccount> getBankAccounts();

    public Map getCustomerBankAccountsMap();

    public Map getCustomerBankAccountOperationsMap();

    public Map getBankAccountsMap();

    @Secured({"ROLE_CUSTOMER","ACL_OBJECT_ADMIN"})
    public void modifyBankAccount(BankAccount bankAccount);

    @Secured({"ROLE_CUSTOMER","ACL_OBJECT_ADMIN"})
    public void modifyBankAccountOperation(BankAccountOperation bankAccountOperation);

    public List<Customer> getAllCustomers();


}
