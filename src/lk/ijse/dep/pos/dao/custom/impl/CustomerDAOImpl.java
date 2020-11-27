package lk.ijse.dep.pos.dao.custom.impl;

import lk.ijse.dep.pos.dao.CrudDAOImpl;
import lk.ijse.dep.pos.dao.custom.CustomerDAO;
import lk.ijse.dep.pos.db.HibernateUtil;
import lk.ijse.dep.pos.entity.Customer;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl extends CrudDAOImpl<Customer,String> implements CustomerDAO {
    

    @Override
    public String getLastCustomerId() throws Exception {
        return (String) getSession().createNativeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1").uniqueResult();
    }

    /*@Override
    public List<Customer> findAll() throws Exception {
       // return session.createNativeQuery("SELECT * FROM customer").list();
        return (List<Customer>)session.createQuery("FROM Customer c",Customer.class).list();
    }

    @Override
    public Customer find(String s) throws Exception {
       return session.get(Customer.class,s);
    }

    @Override
    public void save(Customer entity) throws Exception {
         session.save(entity);
    }

    @Override
    public void update(Customer customer) throws Exception {
        session.merge(customer);
    }

    @Override
    public void delete(String s) throws Exception {
        session.delete(session.load(Customer.class,s));
    }*/


}
