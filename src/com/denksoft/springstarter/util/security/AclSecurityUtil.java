/* Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
*/

package com.denksoft.springstarter.util.security;

import org.springframework.security.acls.sid.Sid;
import org.springframework.security.acls.*;
import com.denksoft.springstarter.entity.AbstractSecureObject;



public interface AclSecurityUtil {

    public void addPermission(AbstractSecureObject securedObject, Permission permission, Class clazz);

    public void addPermission(AbstractSecureObject securedObject, Sid recipient, Permission permission, Class clazz);

    public void deletePermission(AbstractSecureObject securedObject, Sid recipient, Permission permission, Class clazz);

}
