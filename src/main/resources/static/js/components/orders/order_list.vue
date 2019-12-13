<template>
    <div>
        <order-element v-for="order in orders"
                           :order = "order"
                           :key="order.orderID"
                           :editOrder="editOrder"/>
    </div>
</template>

<script>
    import OrderElement from "components/orders/order_element.vue"
    export default {
        name: "OrdersList",
        props: ['orders'],
        components: {
            OrderElement,
        },
        methods: {
            editOrder(order) {
                this.$router.push({path: '/addNew', query: {orderID: order.orderID}})
            }
        },
    }
</script>

<style scoped>
</style>