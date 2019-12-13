<template>
    <v-container>
        <announcements-list :announcements="announcements" />
    </v-container>
</template>

<script>
    import { mapState } from 'vuex'
    import { mapActions } from 'vuex'
    import AnnouncementsList from "components/orders/order_list.vue"
    export default {
        name: "OrdersPage",
        components: {
            OrdersList,
        },
        computed: mapState(['orders']),
        methods: {
            ...mapActions(['getOrderById']),
        },
        created() {
            this.getOrdersById(this.$route.query.id)
        }
    }
</script>

<style scoped>
</style>