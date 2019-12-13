import Vue from 'vue'
import VueRouter from "vue-router"

import main_page from "components/view/main_page.vue"


import order_manage from "components/orders/order_manage.vue";


Vue.use(VueRouter)

const routes = [

    {path: '/', component: main_page},


    {path: '/order_manager', component: order_manage},

]

export default new VueRouter({
    mode: 'history',
    routes
})