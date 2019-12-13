import Vue from 'vue'
import Vuex from 'vuex'
import announcementsApi from "api/announcements"
import userApi from "api/user"

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        order: '',
        orders: [],
        profile: frontendData.profile
    },
    mutations: {
        addOrderMutation(state, order) {
            state.orders = [
                ...state.orders,
                order
            ]
        },
        updateOrderMutation(state, order) {
            const updateIndex = state.orders.findIndex(item => item.id === order.orderid)

            state.orders = [
                ...state.orders.slice(0, updateIndex),
                order,
                ...state.orders.slice(updateIndex + 1)
            ]
        },
        removeOrderMutation(state, order) {
            const deletionIndex = state.orders.findIndex(item => item.id === order.orderid)

            if (deletionIndex > -1) {
                state.orders = [
                    ...state.orders.slice(0, deletionIndex),
                    ...state.orders.slice(deletionIndex + 1)
                ]
            }
        },
        removeAllOrdersMutation(state) {
            state.orders = []
        },
        redactOrderMutation(state, order) {
            state.order = order
        }
    },
    actions: {
        async addOrderAction({commit}, order) {
            const result = await ordersApi.add(order)
            const data = await result.json()
            commit('addOrderMutation', data)
        },
        async updateOrderAction({commit}, order) {
            const result = await ordersApi.update(order)
            const data = await result.json()
            commit('updateOrderMutation', data)
        },
        async removeOrderAction({commit}, order) {
            const result = await ordersApi.remove(order.orderid)
            if (result.ok && result.data === true) {
                commit('removeOrderMutation', order)
            }
        },

        async getOrderById({commit}, id) {
            const result = await ordersApi.getById(id)
            const data = await result.json()

            commit('redactOrderMutation', data)
        }

    }
})