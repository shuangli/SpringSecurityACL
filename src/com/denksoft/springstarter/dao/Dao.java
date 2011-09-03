/* Copyright (c) 2008, DENKSOFT SRL. All rights reserved.
     This software is licensed under the BSD license available at
     http://www.opensource.org/licenses/bsd-license.php, with these parameters:
     <OWNER> = DENKSOFT SRL <ORGANIZATION> = DENKSOFT SRL <YEAR> = 2008
*/

package com.denksoft.springstarter.dao;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class Dao {

    SessionFactory sessionFactory;

    public void persist(Object object) {
        sessionFactory.getCurrentSession().persist(object);
    }
    
    public void save(Object object) {
        sessionFactory.getCurrentSession().save(object);
    }

    public void saveOrUpdate(Object object) {
        sessionFactory.getCurrentSession().saveOrUpdate(object);
    }

    public void update(Object object) {
        sessionFactory.getCurrentSession().update(object);
    }

    public Object load(Class clazz, long id) {
        return sessionFactory.getCurrentSession().load(clazz, id);
    }

    public Object get(Class clazz, long id) {
        return sessionFactory.getCurrentSession().get(clazz, id);
    }

    public Object get(String clazzString, long id) {
        Class clazz = null;
        try {
            clazz = Class.forName(clazzString);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return sessionFactory.getCurrentSession().get(clazz, id);
    }

    public Object load(Class clazz, String id) {
        return sessionFactory.getCurrentSession().load(clazz, id);
    }

    public Object uniqueByField(Class clazz, String fieldName, Object value) throws IndexOutOfBoundsException {
        return sessionFactory.getCurrentSession().createCriteria(clazz).add(Restrictions.eq(fieldName, value)).uniqueResult();
    }

    public List allByField(Class clazz, String fieldName, Object value) {
        return sessionFactory.getCurrentSession().createCriteria(clazz).add(Restrictions.eq(fieldName, value)).list();
    }

    public List loadAll(Class clazz) {
        return sessionFactory.getCurrentSession().createCriteria(clazz).list();        
    }

    public void flush() {
        sessionFactory.getCurrentSession().flush();
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void refresh(Object obj) {
        sessionFactory.getCurrentSession().refresh(obj);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
