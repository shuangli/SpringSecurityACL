/* Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
*/

package com.denksoft.springstarter.util.webflow;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Date;
import java.text.SimpleDateFormat;


public class FlowPropertyEditorRegistrar extends ApplicationObjectSupport implements PropertyEditorRegistrar {

    public void registerCustomEditors(PropertyEditorRegistry registry) {
        String format = getMessageSourceAccessor().getMessage("locale-specific.dateFormat", LocaleContextHolder.getLocale()); 
        registry.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(format),true));
    }
}
