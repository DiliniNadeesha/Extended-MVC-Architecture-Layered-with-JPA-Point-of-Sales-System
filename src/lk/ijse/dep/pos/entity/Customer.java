package lk.ijse.dep.pos.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer implements SuperEntity{
    @Id
    @Column(name = "id")
    private String customerId;
    private String name;
    private String address;

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private  List<Order> orders=new ArrayList<>();


    public Customer() {
    }

    public Customer(String customerId, String name, String address) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }


    //Handler
    public void addOrders(Order order) {
        order.setCustomer(this);
        this.orders.add(order);
    }

    public void removeOrders(Order order) {
        if(order.getCustomer() != this){
            new RuntimeException( "invalid order");
        }
        order.setCustomer(null);
        this.getOrders().remove(order);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", orders=" + orders +
                '}';
    }
}
