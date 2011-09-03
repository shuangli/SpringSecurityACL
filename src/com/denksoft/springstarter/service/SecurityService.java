/* Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
*/

package com.denksoft.springstarter.service;

import com.denksoft.springstarter.entity.security.Authority;
import com.denksoft.springstarter.entity.Customer;
import com.denksoft.springstarter.entity.BankAccount;
import com.denksoft.springstarter.entity.BankAccountOperation;
import com.denksoft.springstarter.entity.Clerk;

import java.util.List;

public interface SecurityService {

    public List<Authority> getAuthorities();

    public Authority getOrCreateAuthorityByRole(String role);

    public void setBankAccountPermissions(BankAccount bankAccount);

    public void setBankAccountOperationPermissions(Customer customer, BankAccountOperation bankAccountOperation);

    public void setBankAccountOperationPermissions(BankAccountOperation bankAccountOperation);

    public void setCustomerPermissions(Customer customer);

    public void setClerkPermissions(Clerk clerk);
}
