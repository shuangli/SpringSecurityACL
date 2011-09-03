/* Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
*/

package com.denksoft.springstarter.service;

import com.denksoft.springstarter.entity.Clerk;
import org.springframework.security.annotation.Secured;


public interface ClerkService {

    @Secured({"ROLE_CLERK","AFTER_ACL_READ"})
    public Clerk getClerk(long id);

}
