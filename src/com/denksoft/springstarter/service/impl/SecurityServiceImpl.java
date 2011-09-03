/* Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
*/

package com.denksoft.springstarter.service.impl;

import com.denksoft.springstarter.entity.security.Authority;
import com.denksoft.springstarter.entity.Customer;
import com.denksoft.springstarter.entity.BankAccount;
import com.denksoft.springstarter.entity.BankAccountOperation;
import com.denksoft.springstarter.entity.Clerk;
import com.denksoft.springstarter.dao.Dao;
import com.denksoft.springstarter.service.SecurityService;
import com.denksoft.springstarter.util.security.AclSecurityUtil;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.acls.sid.Sid;
import org.springframework.security.acls.sid.PrincipalSid;
import org.springframework.security.acls.sid.GrantedAuthoritySid;
import org.springframework.security.acls.domain.BasePermission;

public class SecurityServiceImpl implements SecurityService {

    private static Log log = LogFactory.getLog(SecurityServiceImpl.class);

    @Autowired
    Dao dao;

    @Autowired
    @Qualifier("aclSecurity")
    private AclSecurityUtil aclSecurityUtil;

    public List<Authority> getAuthorities() {
        return (List<Authority>) dao.loadAll(Authority.class);
    }

    public Authority getOrCreateAuthorityByRole(String role) {
        Authority authority;
        try {
            authority = (Authority) dao.uniqueByField(Authority.class, "authority", role);
        } catch (IndexOutOfBoundsException ex) {
            authority = new Authority(role);
        }
        if (authority == null)
            authority = new Authority(role);
        return authority;
    }

    public void setBankAccountPermissions(BankAccount bankAccount) {

        Customer customer = (Customer) dao.load(Customer.class, bankAccount.getCustomer().getId());

        Sid sid = new PrincipalSid(customer.getUser().getUsername());
        Sid sidAdmin = new GrantedAuthoritySid("ROLE_CLERK");

        aclSecurityUtil.addPermission(bankAccount, sid, BasePermission.READ, BankAccount.class);
        aclSecurityUtil.addPermission(bankAccount, sidAdmin, BasePermission.ADMINISTRATION, BankAccount.class);
    }

    public void setBankAccountOperationPermissions(BankAccountOperation bankAccountOperation) {
        Sid sid = new GrantedAuthoritySid("ROLE_CLERK");

        aclSecurityUtil.addPermission(bankAccountOperation, BasePermission.READ, BankAccountOperation.class);
        aclSecurityUtil.addPermission(bankAccountOperation, BasePermission.WRITE, BankAccountOperation.class);
        aclSecurityUtil.addPermission(bankAccountOperation, sid, BasePermission.READ, BankAccountOperation.class);
    }

    public void setBankAccountOperationPermissions(Customer customer, BankAccountOperation bankAccountOperation) {
        Sid sidClerk = new GrantedAuthoritySid("ROLE_CLERK");
        Sid sidCustomer = new PrincipalSid(customer.getUser().getUsername());

        aclSecurityUtil.addPermission(bankAccountOperation, sidCustomer, BasePermission.READ, BankAccountOperation.class);
        aclSecurityUtil.addPermission(bankAccountOperation, sidCustomer, BasePermission.WRITE, BankAccountOperation.class);
        aclSecurityUtil.addPermission(bankAccountOperation, sidClerk, BasePermission.READ, BankAccountOperation.class);
    }

    public void setCustomerPermissions(Customer customer) {
        Sid sid = new PrincipalSid(customer.getUser().getUsername());
        Sid sidAdmin = new GrantedAuthoritySid("ROLE_CLERK");

        aclSecurityUtil.addPermission(customer, sid, BasePermission.ADMINISTRATION, Customer.class);
        aclSecurityUtil.addPermission(customer, sidAdmin, BasePermission.ADMINISTRATION, Customer.class);
    }

    public void setClerkPermissions(Clerk clerk) {
        Sid sid = new PrincipalSid(clerk.getUser().getUsername());
        aclSecurityUtil.addPermission(clerk, sid, BasePermission.ADMINISTRATION, Clerk.class);

    }
}
