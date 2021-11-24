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
        account: '',
        events: [],
        bookedEvents: [],
        id: window.localStorage.getItem('username'),
        name: '',
		    errorEvent: '',
      	response: []
    }
  },
  created: function () {
    AXIOS.get('/events/getAllEvents')
    .then(response => {
      this.events = response.data
    })
    .catch(e => {
      this.errorEvent = e
    })
  },
  methods: {
    bookEvent: function (id, name) {
      var indexEvent = this.events.map(x => x.name).indexOf(name)
      var event = this.events[indexEvent]

      AXIOS.put('/assignEvent/'.concat(id), {}, {
        params: {
          name: name
        }
      })
          .then(response => {
            this.Account = response.data
            //this.Account.events.push(event)
            
            this.bookedEvents = this.Account.events
          })
          .catch(e => {
            this.errorEvent = e
          })
    }
  }

  }

  