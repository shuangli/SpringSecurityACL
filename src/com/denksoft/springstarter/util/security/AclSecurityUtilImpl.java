/* Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
*/

package com.denksoft.springstarter.util.security;

import org.springframework.security.acls.sid.Sid;
import org.springframework.security.acls.sid.PrincipalSid;
import org.springframework.security.acls.objectidentity.ObjectIdentityImpl;
import org.springframework.security.acls.objectidentity.ObjectIdentity;
import org.springframework.security.acls.*;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.denksoft.springstarter.entity.AbstractSecureObject;



public class AclSecurityUtilImpl implements AclSecurityUtil {

    private static Log logger = LogFactory.getLog(AclSecurityUtil.class);

    private MutableAclService mutableAclService;

    public void addPermission(AbstractSecureObject secureObject, Permission permission, Class clazz) {
        addPermission(secureObject, new PrincipalSid(getUsername()), permission, clazz);
    }

    public void addPermission(AbstractSecureObject securedObject, Sid recipient, Permission permission, Class clazz) {
        MutableAcl acl;
        ObjectIdentity oid = new ObjectIdentityImpl(clazz.getCanonicalName(), securedObject.getId());

        try {
            acl = (MutableAcl) mutableAclService.readAclById(oid);
        } catch (NotFoundException nfe) {
            acl = mutableAclService.createAcl(oid);
        }        
                                                                  
        acl.insertAce(acl.getEntries().length, permission, recipient, true);
        mutableAclService.updateAcl(acl);

        if (logger.isDebugEnabled()) {
            logger.debug("Added permission " + permission + " for Sid " + recipient + " securedObject " + securedObject);
        }
    }

    public void deletePermission(AbstractSecureObject securedObject, Sid recipient, Permission permission, Class clazz) {
        ObjectIdentity oid = new ObjectIdentityImpl(clazz.getCanonicalName(), securedObject.getId());
        MutableAcl acl = (MutableAcl) mutableAclService.readAclById(oid);

        // Remove all permissions associated with this particular recipient (string equality to KISS)
        AccessControlEntry[] entries = acl.getEntries();

        for (int i = 0; i < entries.length; i++) {
            if (entries[i].getSid().equals(recipient) && entries[i].getPermission().equals(permission)) {
                acl.deleteAce(i);
            }
        }

        mutableAclService.updateAcl(acl);

        if (logger.isDebugEnabled()) {
            logger.debug("Deleted securedObject " + securedObject + " ACL permissions for recipient " + recipient);
        }
    }

    protected String getUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getPrincipal() instanceof UserDetails) {
            return ((UserDetails) auth.getPrincipal()).getUsername();
        } else {
            return auth.getPrincipal().toString();
        }
    }

    public void setMutableAclService(MutableAclService mutableAclService) {
        this.mutableAclService = mutableAclService;
    }
}