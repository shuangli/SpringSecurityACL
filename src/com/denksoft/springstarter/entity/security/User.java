/* Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
*/

package com.denksoft.springstarter.entity.security;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;


import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.LinkedList;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    private String username;

    private String password;

    private int enabled;

    @ManyToMany
    @Cascade({CascadeType.SAVE_UPDATE})
    private List<Authority> authorities = new LinkedList<Authority>();

    public User() {
        this.enabled = 1;
    }

    public User(String username, String password, int enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }        

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public void setAuthority(Authority authority) {
        this.authorities.add(authority);
    }
}
