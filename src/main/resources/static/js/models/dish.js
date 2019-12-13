import Vue from 'vue'

const person = Vue.resource('/dish{/dishID}')

export default {
    save: dish => dishes.save({}, dish),
    update: dish => dishes.update({dishID: dish.dishID}, dish),
    getById: dishID => dishes.get({dishID}),
    get: data => dishes.get(),
    delete: dish => dishes.delete({dishID: dish.dishID}, dish)
}