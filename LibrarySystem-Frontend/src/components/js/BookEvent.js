import axios from 'axios'
import swal from 'sweetalert'
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
        Account:'',
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
    }),
    AXIOS.get('/checkOutItems')
    .then(response => {
      this.medias = response.data
      // this.id = window.localStorage.getItem('id')
    })
    .catch(e => {
      this.errorEvent = e
    }),
    AXIOS.get('/nonCheckOutItems')
    .then(response => {
      this.medias = response.data
      // this.id = window.localStorage.getItem('id')
    })
    .catch(e => {
      this.errorEvent = e
    }),
    AXIOS.get('/getOnline/'.concat(localStorage.getItem('id')))
    .then(response => {
      this.Account = response.data
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
            swal("Success", "Media " + mediaID + " Reserved Successfully!", "success")
            this.checkedOutMedias = this.Account.medias
          })
          .catch(e => {
            this.errorEvent = e
          })
    },
    updateInfo: function (name, password, address){
      AXIOS.put('/edit_online/'.concat(localStorage.getItem('id')), {}, {
        params: {
          address: address,
          name: name,
          password: password
        }
      })
          .then(response => {
            swal("Success", "Account Updated Successfully!", "success")
          })
          .catch(e => {
            this.errorEvent = e
          })
    }
  }

  }

  