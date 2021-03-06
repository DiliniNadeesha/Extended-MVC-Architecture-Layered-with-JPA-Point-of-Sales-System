package lk.ijse.dep.pos.dao.custom.impl;

import lk.ijse.dep.pos.dao.CrudDAOImpl;
import lk.ijse.dep.pos.dao.custom.OrderDetailDAO;
import lk.ijse.dep.pos.entity.Item;
import lk.ijse.dep.pos.entity.OrderDetail;
import lk.ijse.dep.pos.entity.OrderDetailPK;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDetailDAOImpl extends CrudDAOImpl<OrderDetail,OrderDetailPK> implements OrderDetailDAO {

    @Override
    public boolean existByItemCode(String itemCode) throws Exception {
        return getSession().createNativeQuery("SELECT * FROM OrderDetail WHERE item_code=?1")
                .setParameter(1,itemCode).uniqueResult() !=null;
    }

   /* @Override
    public List<OrderDetail> findAll() throws Exception {
        return session.createQuery("FROM OrderDetail od").list();
    }

    @Override
    public OrderDetail find(OrderDetailPK orderDetailPK) throws Exception {
        return  session.find(OrderDetail.class,orderDetailPK);
    }

    @Override
    public void save(OrderDetail orderDetail) throws Exception {
        session.save(orderDetail);
    }

    @Override
    public void update(OrderDetail orderDetail) throws Exception {
        session.merge(orderDetail);
    }

    @Override
    public void delete(OrderDetailPK orderDetailPK) throws Exception {
        session.delete(session.load(Item.class,orderDetailPK));
    }*/


}
