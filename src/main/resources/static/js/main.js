/*var productApi = Vue.resource('/product{/productID}');


function getIndex(list, productID) {
    for (var i = 0; i < list.length; i++ ) {
        if (list[i].productID === productID) {
            return i;
        }
    }

    return -1;
}

Vue.component('product-form',{
    props: ['products', 'productAttr'],
    data: function() {
        return {
        name:'',
        productID: ''
        }
    },
    watch: {
            productAttr: function(newVal, oldVal) {
                this.name = newVal.name;
                this.personID = newVal.productID;
            }
        },
    template:
    '<div>'+
    '<input type="text" placeholder="Добавить продукт" v-model="name" />' +
    '<input type="button" value="Save" @click="save" />'+
    '</div>',
    methods: {
        save: function() {
            var product = { name: this.name };

            if (this.productID) {
                productApi.update({productID: this.productID}, product).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.products, data.productID);
                        this.products.splice(index, 1, data);
                        this.name = ''
                        this.productID = ''
                    })
                )
            } else {
                productApi.save({}, product).then(result =>
                    result.json().then(data => {
                        this.products.push(data);
                        this.name = ''
                    })
                )
            }
        }
    }
});

Vue.component('product-row', {
    props: ['product', 'editMethod', 'products'],
    template: '<div>' +
              '<i>({{ product.productID }})</i> {{ product.name }}' +
              '<span style="position: absolute; right: 0">' +
              '<input type="button" value="Edit" @click="edit" />' +
              '<input type="button" value="X" @click="del" />' +
              '</span>' +
              '</div>',
    methods: {
            edit: function() {
                this.editMethod(this.product);
            },
            del: function() {
                productApi.remove({ productID: this.product.productID}).then(result => {
                    if (result.ok) {
                        this.products.splice(this.products.indexOf(this.product), 1)
                    }
                })
            }
        }
    });


Vue.component('products-list',{
    props: ['products'],
    data: function() {
        return {
        product: null
        }
    },
    template:
    '<div style="position: relative; width:300px;">' +
    '<product-form :products="products" :productAttr="product" />' +
    '<product-row v-for="product in products" :key="product.productID" :product="product" ' +
    ':editMethod="editMethod" :products="products" />' +
    '</div>',
    created: function(){
        productApi.get().then(result =>
            result.json().then(data =>
                data.forEach(product => this.products.push(product))
            )
        )
    },
    methods: {
        editMethod: function (product) {
            this.product = product;
        }
    }
});*/

import Vue from 'vue'
import Vuetify from "vuetify"
import '@babel/polyfill'
import 'api/resource'
import router from "router/router"
import Vuelidate from "vuelidate"
import App from 'pages/App.vue'
import store from "store/store";
import 'vuetify/dist/vuetify.min.css'

Vue.use(Vuelidate)
Vue.use(Vuetify)

new Vue({
    el: '#app',
    vuetify: new Vuetify(),
    store,
    router,
    render: a => a(App)
})