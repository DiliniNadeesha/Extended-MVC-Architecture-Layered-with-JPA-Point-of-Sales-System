package lk.ijse.dep.pos.util;

//import java.time.LocalDate;

import java.time.LocalDate;

public class SearchOrderTM {
    private Integer orderId;
    private LocalDate date;
    private String customerId;
    private String name;
    private Double total;

    public SearchOrderTM(Integer orderId, LocalDate date, String customerId, String name, Double total) {
        this.setOrderId(orderId);
        this.setDate(date);
        this.setCustomerId(customerId);
        this.setName(name);
        this.setTotal(total);
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "SearchOrderTM{" +
                "orderId=" + orderId +
                ", date=" + date +
                ", customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", total=" + total +
                '}';
    }
}
