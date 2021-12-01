/**
 * @author David Hu 
 * Event.js Gets all the events for the Event.vue
 */

import axios from 'axios'
var config = require('../../../config')
var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})


export default {
  name: 'BookEvent',
  data () {
    return {
        events: [],
        name: '', 
		errorEvent: '',
      	response: []
    }
  },
  created: function () {
    AXIOS.get('/events/getAllEvents')
    .then(response => {
      this.events = response.data
      // this.id = window.localStorage.getItem('id')
    })
  },
  methods: {
    switchToLogin(){
      window.location.href = "#/"
      location.reload()
    },
  }

}