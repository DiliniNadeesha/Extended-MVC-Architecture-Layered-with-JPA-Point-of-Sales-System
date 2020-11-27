package lk.ijse.dep.pos.dao.custom.impl;

import lk.ijse.dep.pos.dao.CrudDAOImpl;
import lk.ijse.dep.pos.dao.custom.OrderDAO;
import lk.ijse.dep.pos.entity.Customer;
import lk.ijse.dep.pos.entity.Order;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class OrderDAOImpl extends CrudDAOImpl<Order,Integer> implements OrderDAO {

    @Override
    public int getLastOrderId() throws Exception {
        Integer lastOderId = (Integer) getSession().createNativeQuery("SELECT id FROM `order` ORDER BY id DESC LIMIT 1").uniqueResult();
        if(lastOderId==null){
            return 0;
        }else{
            return lastOderId;
        }

    }

    @Override
    public boolean existByCustomerId(String customerId) throws Exception {
        return getSession().createNativeQuery("SELECT * FROM `order` WHERE customer_id=?1")
                .setParameter(1,customerId).uniqueResult() !=null;
        

    }

    /*@Override
    public List<Order> findAll() throws Exception {
        return session.createQuery("FROM Order ", Order.class).list();
    }

    @Override
    public Order find(Integer orderId) throws Exception {
        return  session.find(Order.class,orderId);
    }

    @Override
    public void save(Order order) throws Exception {
        session.save(order);
    }

    @Override
    public void update(Order order) throws Exception {
        session.merge(order);
    }

    @Override
    public void delete(Integer orderId) throws Exception {
        session.delete(session.load(Order.class,orderId));
    }*/
}
