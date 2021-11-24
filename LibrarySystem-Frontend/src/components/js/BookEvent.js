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
        onlineName: '',
        onlinePassword: '',
        onlineAddress: '',
        reserveMediaID: '',
        checkOutMediaID: '',
        media: '',
        online: '',
        events: [],
        bookedEvents: [],
        medias: [],
        id: window.localStorage.getItem('id'),
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
    .catch(e => {
      this.errorEvent = e
    })
  },
  methods: {
    bookEvent: function (name) {
      var indexEvent = this.events.map(x => x.name).indexOf(name)
      var event = this.events[indexEvent]

      AXIOS.put('/assignEvent/'.concat(localStorage.getItem('id')), {}, {
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
    },
    reserveItem: function (mediaID){
      AXIOS.put('/reserve_media_online/'.concat(mediaID), {}, {
      })
          .then(response => {
            
          })
          .catch(e => {
            this.errorEvent = e
          })
    }
  }

  }

  