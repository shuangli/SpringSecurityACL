/* Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
*/

package com.denksoft.springstarter.util;

import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;
import org.springframework.dao.CleanupFailureDataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;

public class HibernateFilter extends OpenSessionInViewFilter {

    @Override
    protected Session getSession(SessionFactory sessionFactory) throws DataAccessResourceFailureException {
//        Session session = SessionFactoryUtils.getSession(sessionFactory, true);
        Session session = super.getSession(sessionFactory);
        //set the FlushMode to auto in order to save objects.
        session.setFlushMode(FlushMode.AUTO);
        return session;
    }


    @Override
    protected void closeSession(Session session, SessionFactory sessionFactory) {
        try {
            if (session != null && session.isOpen() && session.isConnected()) {
                try {
                    session.flush();
                } catch (HibernateException e) {
                    throw new CleanupFailureDataAccessException("Failed to flush session before close: " + e.getMessage(), e);
                } catch (Exception e) {
                }
            }
        } finally {
            super.closeSession(session, sessionFactory);
        }
    }
}