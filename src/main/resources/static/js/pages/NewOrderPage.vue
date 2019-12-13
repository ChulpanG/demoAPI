
<template>
    <v-container>
        <announcement-form :announcement="announcement"/>
    </v-container>
</template>

<script>
    import {mapActions} from 'vuex'
    import {mapState} from 'vuex'
    import AnnouncementForm from "components/orders/order_form.vue"
    export default {
        name: "NewOrderPage",
        components: {
            OrderForm
        },
        computed: mapState(['order']),
        methods: mapActions(['getOrderById']),
        created() {
            if (this.$route.query.id) {
                this.getOrderById(this.$route.query.id)
            }
        }
    };
</script>

<style scoped>
</style>