<template>
    <div>
        <v-card ref="form">
            <v-row>
                <v-col cols="2">
                    <v-subheader>Время заказа</v-subheader>
                </v-col>
                <v-col cols="1">
                    <v-text-field
                            solo
                            v-model="orderTime"
                            :error-messages="orderTimeError"
                            @input="$v.orderTime.$touch()"
                            @blur="$v.orderTime.$touch()"
                    />
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="2">
                    <v-subheader>Работник</v-subheader>
                </v-col>
                <v-col cols="1">
                    <v-text-field
                            solo
                            v-model="person"
                            :error-messages="personError"
                            @input="$v.person.$touch()"
                            @blur="$v.person.$touch()"
                    />
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="2">
                    <v-subheader>Блюда</v-subheader>
                </v-col>
                <v-col cols="1">
                    <v-text-field
                            solo
                            v-model="dish"
                            :error-messages="dishError"
                            @input="$v.dish.$touch()"
                            @blur="$v.dish.$touch()"
                    />
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="2">
                    <v-subheader>Номер заказа</v-subheader>
                </v-col>
                <v-col cols="1">
                    <v-text-field
                            solo
                            v-model="orderID"
                            :error-messages="orderIDError"
                            @input="$v.orderID.$touch()"
                            @blur="$v.orderID.$touch()"
                    />
                </v-col>
            </v-row>

            <v-row>
                <v-col cols="3">
                    <v-btn @click="save" color="light-blue" dark>
                        Сохранить
                    </v-btn>
                </v-col>
            </v-row>
        </v-card>
        <v-snackbar
                v-model="snackbar"
                :timeout="timeout"
        >
            {{ text }}
            <v-btn
                    color="blue"
                    text
                    @click="snackbar = false"
            >
                X
            </v-btn>
        </v-snackbar>
    </div>
</template>

<script>
    import {mapActions} from 'vuex'
    import {validationMixin} from 'vuelidate'
    import {required, numeric} from 'vuelidate/lib/validators'
    export default {
        name: "OrderForm",
        props: ['order'],
        mixins: [validationMixin],
        validations: {
            orderTime: {required, numeric},
            person: {required, numeric},
            dish: {required, numeric},

            orderID: {required, numeric}
        },
        data() {
            return {
                orderTime: '',
                person: '',
                dish:'',

                orderID: '',
                snackbar: false,
                text: '',
                timeout: 2000
            }
        },
        watch: {
            order(newVal, oldVal) {
                console.log(newVal)
                this.orderTime = newVal.order.orderTime;
                this.person = newVal.order.person;
                this.dish = newVal.order.dish;

                this.orderID = newVal.orderID;
            }
        },
        methods: {
            ...mapActions(['addOrderAction', 'getOrderById']),
            save() {
                this.$v.$touch()
                if (this.$v.$invalid) {
                } else {
                    const order = {
                        this.orderID = orderID,
                        order: {
                            orderID: this.orderID
                            orderTime: this.orderTime,
                            person: {
                                surname: this.surname,
                                name: this.name
                            },
                            dish: {
                                dishName: this.dishName,
                                price: this.price
                            }}
                    };

                        this.addOrderAction(order)
                        this.text = 'Объявление подано!'
                        this.snackbar = true
                        setTimeout(() => this.$router.go(-1), 2000)

                    this.orderTime = '';
                    this.orderID = '';
                    this.$v.$reset()
                }
            },
        },
        computed: {
            orderTimeError() {
                const errors = []
                if (!this.$v.orderTime.$dirty) return errors
                !this.$v.orderTime.required && errors.push('Введите время')
                !this.$v.orderTime.numeric && errors.push('Только цифровое значение!')
                return errors
            },

        }
    }
</script>

<style scoped>
</style>