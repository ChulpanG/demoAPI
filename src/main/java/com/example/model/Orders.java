package com.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "orders")

public class Orders implements Serializable {

    private int orderID;
    private int personID;
    private Timestamp time;
    private Set<Dish> dish;
    //private Set<OrderDish> orderDish = new HashSet<OrderDish>();
    public Orders(int orderID,int personID,Timestamp time){
        this.orderID=orderID;
        this.personID=personID;
        this.time=time;
    }


    public Orders(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    public int getOrderID(){
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }


    /*@OneToMany(mappedBy = "dish")
    public Set<OrderDish> getOrderDish() {
        return orderDish;
    }

    public void setOrderDish(Set<OrderDish> orderDish) {
        this.orderDish = orderDish;
    }
*/


    @Column(name = "order_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Timestamp getTime(){
        return time;
    }
    public void setTime(Timestamp time) {
        this.time = time;
    }


    public int getPersonID(){
        return personID;
    }
    public void setPersonID(int personID) {
        this.personID = personID;
    }
    private Person person;

    @Access(AccessType.PROPERTY)
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "personid", insertable = false, updatable = false)
    @JsonIgnore
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


    @Access(AccessType.PROPERTY)
    @JoinTable(name = "order_dish", joinColumns = {
            @JoinColumn(name = "orderid", referencedColumnName = "orderid")}, inverseJoinColumns = {
            @JoinColumn(name = "dishid", referencedColumnName = "dishid")})
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            })
    public Set<Dish> getDish() {
        return dish;
    }

    public void setDish(Set<Dish> dish) {
        this.dish = dish;
    }

    /*
        public List<Dish> getOrder(){
            return order;
        }

        public void setOrder(ArrayList<Dish> order) {
            this.order = order;
        }

        public String getDish(){
            String str="";
            for (int i=0;i<order.size();i++) {
                str = str + order.get(i).getDishName()+", ";
            }
            return str;
        }
        public double calculateOrderPrice(){
            double sum=0;
            for (Dish i:order){
                sum+=i.getPrice();
            }
            return sum;
        }
        public void addDishToOrder(Dish dish){
            order.add(dish);
            calculateOrderPrice();
        }
        public void removeDishFromOrder(Dish dish){
            order.remove(dish);
            calculateOrderPrice();
        }
        */
    @Override
    public String toString() {
        return "Order [id=" + orderID + ", person id=" + personID + ", order time" + time + "]";
    }
}

