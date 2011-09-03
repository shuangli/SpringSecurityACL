/* Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
*/

package com.denksoft.springstarter.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tiles.Attribute;
import org.apache.tiles.Definition;
import org.apache.tiles.access.TilesAccess;
import org.apache.tiles.mgmt.MutableTilesContainer;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.support.JstlUtils;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import java.util.Map;


public class TilesViewExtended extends AbstractUrlBasedView {

    private static Log log = LogFactory.getLog(TilesViewExtended.class);
    private MessageSource jstlAwareMessageSource;

    protected void initApplicationContext() {
		super.initApplicationContext();
		this.jstlAwareMessageSource =
				JstlUtils.getJstlAwareMessageSource(getServletContext(), getApplicationContext());
	}

	protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

        MutableTilesContainer container = (MutableTilesContainer) TilesAccess.getContainer(getServletContext());

        log.info("There is no definition registered. Registering a new definition " + getBeanName());
        Definition definition = new Definition();

        Attribute attr = new Attribute();
        attr.setName("body");
        attr.setValue("/WEB-INF/jsp/"+getBeanName()+".jsp");

        definition.addAttribute(attr);
        definition.setExtends("rootLayout");
        definition.setName(getBeanName());

        container.register(definition, request, response);


        exposeModelAsRequestAttributes(model, request);
		JstlUtils.exposeLocalizationContext(request, this.jstlAwareMessageSource);
        //container.render(getUrl(), request, response);

        if (!response.isCommitted()) {
			// Tiles is going to use a forward, but some web containers (e.g. OC4J 10.1.3)
			// do not properly expose the Servlet 2.4 forward request attributes... However,
			// must not do this on Servlet 2.5 or above, mainly for GlassFish compatibility.
			ServletContext sc = getServletContext();
			if (sc.getMajorVersion() == 2 && sc.getMinorVersion() < 5) {
				WebUtils.exposeForwardRequestAttributes(request);
			}
		}

		container.render(getUrl(), new Object[] {request, response});
    }

}