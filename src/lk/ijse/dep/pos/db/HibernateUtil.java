package lk.ijse.dep.pos.db;
import lk.ijse.dep.crypto.DEPDecoder;
import lk.ijse.dep.pos.entity.Customer;
import lk.ijse.dep.pos.entity.Item;
import lk.ijse.dep.pos.entity.Order;
import lk.ijse.dep.pos.entity.OrderDetail;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HibernateUtil {

    private static SessionFactory sessionFactory=buildSessionFactory();
    private static String user;
    private static String password;
    private static String db;
    private static String host;
    private static String port;

    private static SessionFactory buildSessionFactory(){

        File file = new File("resources/application.properties");
        Properties properties = new Properties();
        try(FileInputStream fis =new FileInputStream(file)) {
            properties.load(fis);
        } catch (IOException e) {
            Logger.getLogger("lk.ijse.dep.pos.db.HibernateUtil").log(Level.SEVERE,null,e);
            System.exit(2);
        }

        user=  DEPDecoder.decode(properties.getProperty("hibernate.connection.username"),"dep");
        password= DEPDecoder.decode(properties.getProperty("hibernate.connection.password"),"dep");
        host=properties.getProperty("ijse.dep.ip");
        db=properties.getProperty("ijse.dep.db");
        port=properties.getProperty("ijse.dep.port");
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
//                .configure( ) // default path 1ta daapu nisa path 1 denna oni na  .configure( "org/lk.ijse.dep.hibernate/example/lk.ijse.dep.hibernate.cfg.xml" )

                .loadProperties(file)
                .applySetting("hibernate.connection.username",user)
                .applySetting("hibernate.connection.password",password)
                .build();

        Metadata metadata = new MetadataSources( standardRegistry )
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(OrderDetail.class)
                .addAnnotatedClass(Order.class)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy( ImplicitNamingStrategyJpaCompliantImpl.INSTANCE )
                .build();

//        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
//                .build();

        return metadata.getSessionFactoryBuilder().build();
    }
    public static  SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    public static String getUser(){return user;}
    public static String getPassword(){return password;}
    public static String getDb(){return db;}
    public static String getHost(){return host;}
    public static String getPort(){return port;}
}
