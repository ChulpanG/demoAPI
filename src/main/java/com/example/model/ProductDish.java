package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(value=ProductDish.ProductDishId.class)
@Table(name = "product_dish")
public class ProductDish implements Serializable{

    private int dishID;
    private int productID;
    private int id;
    public ProductDish(int dishID, int productID) {
        this.dishID = dishID;
        this.productID = productID;
    }

    public ProductDish(){ }
    @Id
    @Column(name = "dishid")
    public int getDishID() {
        return dishID;
    }

    public void setDishID(int dishID) {
        this.dishID = dishID;
    }
    @Id
    @Column(name = "productid")
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    static class ProductDishId implements Serializable {
        private int productID;
        private int dishID;

        public int getProductID() {
            return productID;
        }

        public void setProductID(int productID) {
            this.productID = productID;
        }

        public int getDishID() {
            return dishID;
        }

        public void setDishID(int dishID) {
            this.dishID = dishID;
        }
    }

}
