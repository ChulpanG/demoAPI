<template>
    <v-app>
        <v-app-bar app color="white">
            <v-toolbar-title>Учет заказов</v-toolbar-title>

            <v-divider
                    class="mx-4"
                    vertical
            ></v-divider>

            <v-btn
                    text
                    href="/"
            >
                Главная
            </v-btn>

            <div class="flex-grow-1"></div>

            <sign-in-form v-if="!profile"></sign-in-form>
            <div v-else>

                <v-btn
                        text
                        :disabled="$route.path ==='/addNew'"
                        @click="addNew"
                >
                    Добавить заказ
                </v-btn>
            </div>
        </v-app-bar>
        <v-content>
            <router-view></router-view>
        </v-content>
    </v-app>
</template>

<script>
    import {mapState} from 'vuex'
    import OrderList from "components/orders/order_list.vue"
    export default {
        name: "App",
        components: {
            OrderList,
        },

        methods: {

            addNew() {
                this.$router.push('/addNew')
            }
        },

    }
</script>

<style scoped>
</style>