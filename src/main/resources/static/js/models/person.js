import Vue from 'vue'

const person = Vue.resource('/person{/personid}')

export default {
    save: person => persons.save({}, person),
    update: person => persons.update({personid: person.personid}, person),
    getById: id => persons.get({personid}),
    get: data => persons.get(),
    delete: person => persons.delete({personid: person.personid}, person)
}