/* Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
*/

package com.denksoft.springstarter.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.ModelMap;


import javax.servlet.http.HttpServletRequest;

import com.denksoft.springstarter.service.ClerkService;
import com.denksoft.springstarter.service.CustomerService;
import com.denksoft.springstarter.service.PublicService;
import com.denksoft.springstarter.entity.*;


@Controller
public class WelcomeController {

    private static Log log = LogFactory.getLog(WelcomeController.class);

    @Autowired
    @Qualifier("clerkService")
    ClerkService clerkService;

    @Autowired
    @Qualifier("customerService")
    CustomerService customerService;

    @Autowired
    @Qualifier("publicService")
    PublicService publicService;

    @RequestMapping({"/index.task","/public/welcome.task","/customer/welcome.task","/clerk/welcome.task"})
    public void index() {
    }

    @RequestMapping({"/public/populate.task"})
    public void populateDatabase(HttpServletRequest request) {
        request.setAttribute("populated", publicService.populate());
        request.setAttribute("users", publicService.getUsers());
    }

    @RequestMapping({"/customer/viewBankAccounts.task"})
    public void viewBankAccounts(ModelMap map) {
        map.put("bankAccounts", customerService.getBankAccountsMap());
    }

    @RequestMapping({"/customer/viewBankAccountOperations.task"})
    public void viewBankAccountOperations(ModelMap map) {
        map.put("bankAccountOperations", customerService.getCustomerBankAccountOperationsMap());
    }

    @RequestMapping({"/customer/viewBankAccount.task"})
    public void viewBankAccount(ModelMap map, long id) {
        map.put("bankAccount", customerService.getBankAccount(id));
    }

    @RequestMapping({"/customer/viewBankAccountOperation.task"})
    public void viewBankAccountOperation(ModelMap map, long id) {
        map.put("bankAccountOperation", customerService.getBankAccountOperation(id));
    }

    @RequestMapping({"/customer/modifyBankAccount.task"})
    public void modifyAccount(@RequestParam("id") long id) {
        BankAccount account = customerService.getBankAccount(id);
        customerService.modifyBankAccount(account);
    }

    @RequestMapping({"/customer/modifyBankAccountOperation.task"})
    public void modifyBankAccountOperation(long id) {
        BankAccountOperation bankAccountOperation = customerService.getBankAccountOperation(id);
        customerService.modifyBankAccountOperation(bankAccountOperation);
    }
}
