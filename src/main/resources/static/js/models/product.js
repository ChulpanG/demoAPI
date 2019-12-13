import Vue from 'vue'

const person = Vue.resource('/product{/productid}')

export default {
    save: product => products.save({}, product),
    update: product => products.update({productid: product.productid}, product),
    getById: id => products.get({productid}),
    get: data => products.get(),
    delete: product => products.delete({productid: product.productid}, product)
}