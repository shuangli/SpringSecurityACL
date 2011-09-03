/* Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
*/

package com.denksoft.springstarter.service.impl;

import com.denksoft.springstarter.service.PublicService;
import com.denksoft.springstarter.service.SecurityService;
import com.denksoft.springstarter.entity.Customer;
import com.denksoft.springstarter.entity.Clerk;
import com.denksoft.springstarter.entity.BankAccount;
import com.denksoft.springstarter.entity.Marker;
import com.denksoft.springstarter.entity.security.User;
import com.denksoft.springstarter.entity.security.Authority;
import com.denksoft.springstarter.dao.Dao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PublicServiceImpl implements PublicService {
    private static Log log = LogFactory.getLog(PublicServiceImpl.class);

    @Autowired
    Dao dao;

    @Autowired
    SecurityService securityService;

    @Transactional
    public boolean populate() {

        //Check marker if database is populated with sample 
        Marker marker = (Marker) dao.uniqueByField(Marker.class, "sampleCustomersPopulated", true);
        if (marker != null)
            return false;

        //add clerk
        User user = new User("Clark.Kent", "a", 1);
        Authority auth = securityService.getOrCreateAuthorityByRole("ROLE_CLERK");
        user.setAuthority(auth);
        dao.save(user);
        Clerk clerk = new Clerk("Clark", "Kent", user);
        dao.save(clerk);
        securityService.setClerkPermissions(clerk);

        //add user 1
        User userCustomer1 = new User("John.Doe", "a", 1);
        Authority authCustomer1 = securityService.getOrCreateAuthorityByRole("ROLE_CUSTOMER");
        userCustomer1.setAuthority(authCustomer1);
        dao.save(userCustomer1);
        Customer customer1 = new Customer("John", "Doe", "John.Doe@mail.com", "+402323529998");
        customer1.setUser(userCustomer1);
        dao.save(customer1);
        securityService.setCustomerPermissions(customer1);

        //creating bank account 1 for customer 1
        BankAccount bankAccount1 = new BankAccount("RO888XX4563018282", 3000.0);
        bankAccount1.setCustomer(customer1);
        dao.save(bankAccount1);
        securityService.setBankAccountPermissions(bankAccount1);

        //creating bank account 2 for customer 1
        BankAccount bankAccount2 = new BankAccount("RO727XYZ233019932", 5000.0);
        bankAccount2.setCustomer(customer1);
        dao.save(bankAccount2);
        securityService.setBankAccountPermissions(bankAccount2);

        //add user 2
        User userCustomer2 = new User("Jane.Doe", "a", 1);
        Authority authCustomer2 = securityService.getOrCreateAuthorityByRole("ROLE_CUSTOMER");
        userCustomer2.setAuthority(authCustomer2);
        dao.save(userCustomer2);
        Customer customer2 = new Customer("Jane", "Doe", "Jane.Doe@mail.com", "+4023211298");
        customer2.setUser(userCustomer2);
        dao.save(customer2);
        securityService.setCustomerPermissions(customer2);

        //creating bank account 1 for customer 1
        BankAccount bankAccount3 = new BankAccount("RO122XC4332013312", 1200.0);
        bankAccount3.setCustomer(customer2);
        dao.save(bankAccount3);
        securityService.setBankAccountPermissions(bankAccount3);

        //creating bank account 2 for customer 1
        BankAccount bankAccount4 = new BankAccount("RO237CCZ211012354", 4000.0);
        bankAccount4.setCustomer(customer2);
        dao.save(bankAccount4);
        securityService.setBankAccountPermissions(bankAccount4);

        //Add marker that database was populated
        marker = new Marker(true);
        dao.save(marker);

        return true;
    }

    public List<User> getUsers() {
        return dao.loadAll(User.class);
    }

}
