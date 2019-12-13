<template>
    <v-container>
        <v-row align="center">
            <v-col lg="2">
                <v-text-field
                        name="order"
                        label="Value"
                        v-model="order_orderID">
                </v-text-field>
            </v-col>
            <v-col lg="2">
                <v-select
                        :items="items"
                        label="Type of search"
                        dense
                        v-model="choice">
                </v-select>
            </v-col>
            <v-col lg="2">
                <v-btn color="light-blue" dark @click="find">
                    <v-icon dark left>search</v-icon>
                </v-btn>
            </v-col>
        </v-row>
        <v-row>
            <v-container>
                <div class="text--primary">
                    Orders
                </div>
                <v-row dense>
                    <v-col cols="6" sm="4">
                        <v-container fluid>
                            <v-card class="mx-auto" v-for="order in this.ordersAll" :order="order" :key="order.orderID">
                                <v-container>
                                    <v-row>
                                        <v-col class="d-flex" cols="12" sm="6">
                                            <order-element :value="order.orderID" label="Id"></order-element>
                                        </v-col>
                                    </v-row>
                                    <v-row>
                                        <v-col class="d-flex" cols="12" sm="6">
                                            <order-element :value="order.time" label="Time"></order-element>
                                        </v-col>
                                    </v-row>
                                    <v-row>
                                        <v-col class="d-flex" cols="12" sm="6">
                                            <order-element :value="order.personID" label="Weight"></order-element>
                                        </v-col>
                                    </v-row>
                                    <v-row>
                                        <v-col class="d-flex" cols="12" sm="6">
                                            <v-chip class="ma-2" v-for="person in order.special_persons">
                                                {{person.name}}
                                            </v-chip>
                                        </v-col>
                                    </v-row>

                                    <v-row justify="space-around">
                                        <v-col class="d-flex" cols="12" sm="6">
                                            <!--                                        <v-btn color="black" dark icon>-->
                                            <!--                                            <v-icon>edit</v-icon>-->
                                            <!--                                        </v-btn>-->
                                            <v-btn color="black" dark icon @click="deleteOrder(order)">
                                                <v-icon>delete</v-icon>
                                            </v-btn>
                                        </v-col>
                                    </v-row>
                                </v-container>
                            </v-card>
                        </v-container>
                    </v-col>
                </v-row>
            </v-container>
        </v-row>
    </v-container>
</template>

<script>
    import {mapState} from 'vuex'
    import { mapActions } from 'vuex'

    import OrderElement from "components/orders/order_element.vue";
    export default {
        name: "order_managment",
        components: {OrderElement},
        data(){
            return{
                orderID: '',
                items: ['By id'],
                choice: '',
            }
        },
        methods: {
            ...mapActions(['getAllOrders', 'findOrderById', 'removeOrder']),
            find(){
                if (this.choice == "By id"){
                    this.findOrderById(this.orderID)
                }


                if (this.orderID == ''){
                    this.getAllOrders()
                }
            },
            deleteOrder(order){
                this.removeOrder(order)
                this.getAllOrders()
            }
        },
        computed: {
            ...mapState(['ordersAll']),
        },
        created(){
            this.getAllOrders()
        }
    }
</script>

<style scoped>
</style>