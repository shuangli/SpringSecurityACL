/* Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
*/

package com.denksoft.springstarter.service.impl;

import com.denksoft.springstarter.service.CustomerService;
import com.denksoft.springstarter.service.SecurityService;
import com.denksoft.springstarter.entity.Customer;
import com.denksoft.springstarter.entity.BankAccount;
import com.denksoft.springstarter.entity.BankAccountOperation;
import com.denksoft.springstarter.entity.security.User;
import com.denksoft.springstarter.dao.Dao;
import com.denksoft.springstarter.util.security.CustomUserDetailsWrapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;

import java.util.*;


public class CustomerServiceImpl implements CustomerService {

    private static Log log = LogFactory.getLog(CustomerServiceImpl.class);

    @Autowired
    Dao dao;

    @Autowired
    SecurityService securityService;

    public Customer setupCustomer(Customer customer)  {
        customer.getUser().setAuthority(securityService.getOrCreateAuthorityByRole("ROLE_CUSTOMER"));
        return customer;
    }

    public Customer getCustomer(long id) {
        return (Customer) dao.get(Customer.class, id);
    }

    public BankAccount getBankAccount(long id) {
        return (BankAccount) dao.get(BankAccount.class, id);
    }

    public BankAccountOperation getBankAccountOperation(long id) {
        return (BankAccountOperation) dao.get(BankAccountOperation.class, id);
    }

    public Collection<BankAccount> getCustomerBankAccounts() {
        CustomUserDetailsWrapper userDetailsWrapper = (CustomUserDetailsWrapper) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = (User) dao.load(User.class, userDetailsWrapper.getUsername());
        Customer customer = (Customer) dao.uniqueByField(Customer.class, "user", user);
        List myList = dao.allByField(BankAccount.class, "customer", customer);
        return myList;
    }

    public Collection<BankAccountOperation> getCustomerBankAccountOperations() {
        List myList = new ArrayList<BankAccountOperation>();
        Collection<BankAccount> bankAccounts = getCustomerBankAccounts();
        for (BankAccount account : bankAccounts) {
            myList.addAll(account.getOperations());
        }
        return myList;
    }

    public Collection<BankAccount> getBankAccounts() {
        return dao.loadAll(BankAccount.class);
    }

    public Map getCustomerBankAccountsMap() {
        Collection<BankAccount> bankAccounts = getCustomerBankAccounts();
        Map<Long, BankAccount> bankAccountMap = new HashMap<Long, BankAccount>();

        for (BankAccount account : bankAccounts) {
            bankAccountMap.put(account.getId(), account);
        }

        return bankAccountMap;
    }

    public Map getCustomerBankAccountOperationsMap() {
        Collection<BankAccountOperation> bankAccountOperations = getCustomerBankAccountOperations();
        Map<Long, BankAccountOperation> bankAccountOperationsMap = new HashMap<Long, BankAccountOperation>();

        for (BankAccountOperation operation : bankAccountOperations) {
            bankAccountOperationsMap.put(operation.getId(), operation);
        }

        return bankAccountOperationsMap;
    }

    public Map getBankAccountsMap() {
        Collection<BankAccount> bankAccounts = getBankAccounts();
        Map<Long, BankAccount> bankAccountMap = new HashMap<Long, BankAccount>();

        for (BankAccount account : bankAccounts) {
            bankAccountMap.put(account.getId(), account);
        }

        return bankAccountMap;
    }

    public void modifyBankAccount(BankAccount bankAccount) {
        //do nothing for now
    }

    public void modifyBankAccountOperation(BankAccountOperation bankAccountOperation) {
        //do nothing now
    }

    public List<Customer> getAllCustomers() {
        return dao.loadAll(Customer.class);
    }

}