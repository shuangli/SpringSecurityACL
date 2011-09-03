/* Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
*/

package com.denksoft.springstarter.util.conversion;

import org.springframework.binding.convert.service.DefaultConversionService;
import com.denksoft.springstarter.entity.BankAccountOperation;

/**
 * Created by IntelliJ IDEA.
 * User: andrei
 * Date: Aug 25, 2008
 * Time: 2:30:31 PM
 */
public class ApplicationConversionService extends DefaultConversionService {

    @Override
    protected void addDefaultConverters() {
        super.addDefaultConverters();
        addConverter(new StringToOperationType());
    }

    protected void addDefaultAliases() {
        super.addDefaultAliases();
        addAlias("operationTypeConverter", BankAccountOperation.OperationType.class);
    }
}