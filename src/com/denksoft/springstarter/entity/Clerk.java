/* Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
*/

package com.denksoft.springstarter.entity;

import com.denksoft.springstarter.entity.security.User;

import javax.persistence.*;
import java.io.Serializable;
             

@Entity
public class Clerk extends AbstractSecureObject implements Serializable {

    private String firstName;

    private String lastName;

    @OneToOne
    @JoinColumn(name = "username")
    private User user  = new User();

    public Clerk() {
    }

    public Clerk(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Clerk(String firstName, String lastName, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}