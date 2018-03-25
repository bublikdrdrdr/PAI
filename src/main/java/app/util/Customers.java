package app.util;

import app.bean.CustomerBean;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Objects;

public class Customers {
    public List<CustomerBean> getCustomers() {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        Object o = s.createQuery("from app.bean.CustomerBean").list();
        List<CustomerBean> customers = (List)o;
        s.close();
        return customers;
    }

    public int insert(CustomerBean customerBean){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return (int)session.save(customerBean);
        } finally {
            session.close();
        }
    }
}
