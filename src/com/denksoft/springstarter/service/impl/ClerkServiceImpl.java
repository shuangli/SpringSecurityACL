/* Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
*/

package com.denksoft.springstarter.service.impl;

import com.denksoft.springstarter.dao.Dao;
import com.denksoft.springstarter.entity.Clerk;
import com.denksoft.springstarter.entity.security.User;
import com.denksoft.springstarter.entity.security.Authority;
import com.denksoft.springstarter.service.ClerkService;
import com.denksoft.springstarter.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ClerkServiceImpl implements ClerkService {

    private static Log log = LogFactory.getLog(ClerkServiceImpl.class);

    @Autowired
    Dao dao;

    @Autowired
    SecurityService securityService;

    public Clerk getClerk(long id) {
        Clerk clerk = (Clerk)dao.get(Clerk.class, id);
        if (clerk == null) {
            clerk = new Clerk();
            User u = new User();
            u.setEnabled(1);
            Authority auth = securityService.getOrCreateAuthorityByRole("ROLE_CLERK");
            u.setAuthority(auth);
            clerk.setUser(u);
        }
        return clerk;
    }
}
