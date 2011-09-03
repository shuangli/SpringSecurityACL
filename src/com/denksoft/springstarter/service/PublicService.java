/* Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
*/

package com.denksoft.springstarter.service;

import com.denksoft.springstarter.entity.security.User;

import java.util.List;


public interface PublicService {
   
    public boolean populate();

    public List<User> getUsers();
}
