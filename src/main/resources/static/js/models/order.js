import Vue from 'vue'

const orders = Vue.resource('/orders{/orderID}')

export default {
    save: order => orders.save({}, order),
    update: order => orders.update({orderID: order.orderID}, order),
    getById: orderID => orders.get({orderID}),
    get: data => orders.get(),
    delete: order => orders.delete({orderID: order.orderID}, order)
}