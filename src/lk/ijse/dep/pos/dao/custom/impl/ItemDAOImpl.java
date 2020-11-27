package lk.ijse.dep.pos.dao.custom.impl;

import lk.ijse.dep.pos.dao.CrudDAOImpl;
import lk.ijse.dep.pos.dao.custom.ItemDAO;
import lk.ijse.dep.pos.entity.Item;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDAOImpl extends CrudDAOImpl<Item,String> implements ItemDAO {

    @Override
    public String getLastItemCode() throws Exception {
       return (String) getSession().createNativeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1").uniqueResult();
       
    }
/*
    @Override
    public List<Item> findAll() throws Exception {
       return session.createQuery("FROM Item i",Item.class).list();
    }

    @Override
    public Item find(String itemCode) throws Exception {
        return  session.find(Item.class,itemCode);
    }

    @Override
    public void save(Item item) throws Exception {
        session.save(item);
    }

    @Override
    public void update(Item item) throws Exception {
       session.merge(item);
    }

    @Override
    public void delete(String itemCode) throws Exception {
        session.delete(session.load(Item.class,itemCode));
    }*/


}
