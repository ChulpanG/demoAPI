   import Vue from 'vue'
   
   const orderDish = Vue.resource('/orderDish{/orderid}')
   
   export default {
       save: orderDish => orderDishes.save({}, orderDish),
       update: orderDish => orderDishes.update({dishid: orderDish.dishid}, orderDish),
       getById: id => orderDishes.get({dishid}),
       get: data => orderDishes.get(),
       delete: orderDish => orderDishes.delete({dishid: orderDish.dishid}, orderDish)
   }