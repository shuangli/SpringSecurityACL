/* Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
*/

package com.denksoft.springstarter.util.security;

import org.springframework.security.*;
import org.springframework.security.afterinvocation.AclEntryAfterInvocationProvider;
import org.springframework.security.acls.AclService;
import org.springframework.security.acls.Permission;
import org.springframework.security.acls.IdentityUnavailableException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Method;
import java.io.Serializable;


public class CustomAclEntryAfterInvocationProvider extends AclEntryAfterInvocationProvider {

    protected static final Log logger = LogFactory.getLog(CustomAclEntryAfterInvocationProvider.class);

    public CustomAclEntryAfterInvocationProvider(AclService aclService, Permission[] requirePermission) {
        super(aclService, requirePermission);
    }


    public Object decide(Authentication authentication, Object object, ConfigAttributeDefinition config, Object returnedObject) throws AccessDeniedException {
        if(returnedObject != null)
            if(Integer.parseInt(getIdentity(returnedObject).toString()) == 0)
                return returnedObject;
        return super.decide(authentication, object, config, returnedObject);
    }

    private Serializable getIdentity(Object object) throws IdentityUnavailableException {
        Assert.notNull(object, "object cannot be null");
        Serializable identifier;
        Class javaType = ClassUtils.getUserClass(object.getClass());

        Object result;

        try {
            Method method = javaType.getMethod("getId", new Class[] {});
            result = method.invoke(object, new Object[] {});
        } catch (Exception e) {
            throw new IdentityUnavailableException("Could not extract identity from object " + object, e);
        }

        Assert.notNull(result, "getId() is required to return a non-null value");
        Assert.isInstanceOf(Serializable.class, result, "Getter must provide a return value of type Serializable");
        return (Serializable) result;
    }
}
