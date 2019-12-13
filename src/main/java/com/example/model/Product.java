package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product implements Serializable {

    int productID;
    String name;
    private Set<Dish> dish = new HashSet<>();

    Product(int productID,String name){
        this.productID=productID;
        this.name=name;

    }

    Product(){ }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Access(AccessType.PROPERTY)
    @JoinTable(name = "product_dish", joinColumns = {
            @JoinColumn(name = "productid", referencedColumnName = "productid")}, inverseJoinColumns = {
            @JoinColumn(name = "dishid", referencedColumnName = "dishid")})
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            })
    @JsonIgnore
    public Set<Dish> getDish() {
        return dish;
    }

    public void setDish(Set<Dish> dish) {
        this.dish = dish;
    }

    @Override
    public String toString() {
        return "Product [id=" + productID + ", product name=" + name + "]";
    }
}
