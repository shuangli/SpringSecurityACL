/* Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
*/

package com.denksoft.springstarter.util.conversion;

import org.springframework.binding.convert.converters.StringToObject;
import com.denksoft.springstarter.entity.BankAccountOperation;

public class StringToOperationType extends StringToObject {

    public StringToOperationType() {
        super(BankAccountOperation.OperationType.class);
    }

    protected Object toObject(String string, Class objectClass) {
        return BankAccountOperation.OperationType.valueOf(string);
    }

    protected String toString(Object object) {
        return object.toString();
    }
}