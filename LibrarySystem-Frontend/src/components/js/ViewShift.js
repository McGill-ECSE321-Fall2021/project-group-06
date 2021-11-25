import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'Shift',
    data () {
      return {
          shifts: [],
          errorEvent: '',
          response: []
      }
    },
    created: function () {
        AXIOS.get('/viewPersonalSchedule/'.concat(localStorage.getItem('id')))
        .then(response => {
          this.shifts = response.data
        })
        .catch(e => {
          this.errorEvent = e
        })
    },
    
    methods: {
    }
  
}