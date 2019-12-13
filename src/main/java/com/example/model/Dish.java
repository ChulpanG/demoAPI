package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dish")
public class Dish implements Serializable {
    int dishID;
    String dishName;
    double price;
    private Set<Product> product = new HashSet<>();
    private Set<Orders> order = new HashSet<>();
    public Dish(String dishName,double price){

        this.dishName=dishName;
        this.price=price;

    }
    public Dish(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getDishID() {
        return dishID;
    }

    public void setDishID(int dishID) {
        this.dishID = dishID;
    }

    /*@OneToMany(mappedBy = "orders")
    public Set<OrderDish> getOrderDish() {
        return order;
    }

    public void setOrderDish(Set<OrderDish> orderDish) {
        this.order = orderDish;
    }*/

    @Column(name = "dishname")
    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Access(AccessType.PROPERTY)
    @JoinTable(
            name = "product_dish",
            joinColumns = { @JoinColumn(name = "dishid", referencedColumnName = "dishid")},
            inverseJoinColumns = {@JoinColumn(name = "productid", referencedColumnName = "productid")})
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            })
    public Set<Product> getProduct() {
        return product;
    }

    public void setProduct(Set<Product> product) {
        this.product = product;
    }

    @Access(AccessType.PROPERTY)
    @JoinTable(name = "order_dish",
            joinColumns = {
            @JoinColumn(name = "dishid", referencedColumnName = "dishid")}, inverseJoinColumns = {
            @JoinColumn(name = "orderid", referencedColumnName = "orderid")})
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            })
    @JsonIgnore
    public Set<Orders> getOrder() {
        return order;
    }

    public void setOrder(Set<Orders> order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Dish [id=" + dishID + ", dish name=" + dishName + ", price" + price + "]";
    }
}