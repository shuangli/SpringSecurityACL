/* Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
*/

package com.denksoft.springstarter.util.webflow;

import java.io.Serializable;

public class ProgressInfo implements Serializable {

    private int stateNumber;
    private int stateTotal;
    private String flowName;

    public ProgressInfo() {
    }

    public ProgressInfo(String flowName) {
        this.flowName = flowName;
    }

    public int getStateNumber() {
        return stateNumber;
    }

    public void setStateNumber(int stateNumber) {
        this.stateNumber = stateNumber;
    }

    public int getStateTotal() {
        return stateTotal;
    }

    public void setStateTotal(int stateTotal) {
        this.stateTotal = stateTotal;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProgressInfo that = (ProgressInfo) o;

        if (stateNumber != that.stateNumber) return false;
        if (stateTotal != that.stateTotal) return false;
        if (!flowName.equals(that.flowName)) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = stateNumber;
        result = 31 * result + stateTotal;
        result = 31 * result + flowName.hashCode();
        return result;
    }

   /* public String toString() {
        return "Flow " + flowName + " State " + stateNumber + " of " + stateTotal;
    }*/
}
