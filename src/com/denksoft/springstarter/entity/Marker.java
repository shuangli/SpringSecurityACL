/* Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
*/

package com.denksoft.springstarter.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
public class Marker {
    
    @Id
    @GeneratedValue
    private long id;

    private boolean sampleCustomersPopulated = false;

    public Marker() {
    }

    public Marker(boolean sampleCustomersPopulated) {
        this.sampleCustomersPopulated = sampleCustomersPopulated;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isSampleCustomersPopulated() {
        return sampleCustomersPopulated;
    }

    public void setSampleCustomersPopulated(boolean sampleCustomersPopulated) {
        this.sampleCustomersPopulated = sampleCustomersPopulated;
    }
}
