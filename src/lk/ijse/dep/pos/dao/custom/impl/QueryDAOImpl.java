package lk.ijse.dep.pos.dao.custom.impl;

import lk.ijse.dep.pos.dao.custom.QueryDAO;
import lk.ijse.dep.pos.entity.CustomEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QueryDAOImpl implements QueryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public CustomEntity getOrderInfo(int orderId) throws Exception {
        return (CustomEntity) getSession().createQuery("SELECT NEW lk.ijse.dep.pos.entity.CustomEntity(o.id, c.id,c.name,o.date) FROM Customer c INNER JOIN c.orders o WHERE o.id=?1")
                .setParameter(1, orderId)
                .uniqueResult();
    }

    @Override

    public CustomEntity getOrderInfo2(int orderId) throws Exception {
   /*   //  return (CustomEntity) session.createQuery("SELECT new lk.ijse.dep.hibernate.entity.CustomEntity(o.id,o.date,SUM(od.qty* od.unitPrice),c.id,c.name) FROM Order o INNER JOIN  o.orderDetails od INNER JOIN o.customer c GROUP BY o.id", CustomEntity.class);
        NativeQuery nativeQuery = session.createNativeQuery("SELECT o.id AS order_id,o.date AS date, SUM(od.qty * od.unit_price) AS total, co.customer_id AS customer_id, c.name AS name " +
                "FROM `order`o INNER JOIN order_detail od ON o.id=od.order_id INNER JOIN customer_order co on o.id=co.order_id " +
                "INNER JOIN customer c ON co.customer_id = c.id GROUP BY o.id");

        return (CustomEntity)nativeQuery.setResultTransformer(Transformers.aliasToBean(CustomEntity.class));*/

   return null;

    }

    @Override
    public List<CustomEntity> getOrdersInfo(String query) throws Exception {
        NativeQuery nativeQuery = getSession().createNativeQuery("SELECT O.id AS orderId, C.id AS customerId, C.name AS customerName, O.date AS orderDate, SUM(OD.qty * OD.unit_price) AS orderTotal  FROM Customer C INNER JOIN `Order` O ON C.id=O.customer_id " +
                "INNER JOIN OrderDetail OD on O.id = OD.order_id WHERE O.id LIKE ?1 OR C.id LIKE ?2 OR C.name LIKE ?3 OR O.date LIKE ?4 GROUP BY O.id");
        nativeQuery.setParameter(1,query);
        nativeQuery.setParameter(2,query);
        nativeQuery.setParameter(3,query);
        nativeQuery.setParameter(4,query);
       Query query1 = nativeQuery.setResultTransformer(Transformers.aliasToBean(CustomEntity.class));
        List<CustomEntity> list = query1.list();
        return list;

      /*  ResultSet rst = CrudUtil.execute("SELECT O.id, C.customerId, C.name, O.date, SUM(OD.qty * OD.unitPrice) AS Total  FROM Customer C INNER JOIN `Order` O ON C.customerId=O.customerId " +
                "INNER JOIN OrderDetail OD on O.id = OD.orderId WHERE O.id LIKE ? OR C.customerId LIKE ? OR C.name LIKE ? OR O.date LIKE ? GROUP BY O.id", query,query,query,query);
        List<CustomEntity> al = new ArrayList<>();
        while (rst.next()){
            al.add(new CustomEntity(rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDate(4),
                    rst.getDouble(5)));
        }
        return al;
    }*/

    }
}
