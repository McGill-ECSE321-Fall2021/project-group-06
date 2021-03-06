/**
 * @author David Hu 
 * BookEvent.js has all the methods used by Online users
 */

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
    AXIOS.get('/getOnline/'.concat(localStorage.getItem('id')))
    .then(response => {
      this.Account = response.data
      this.events = response.data.events
      this.medias = response.data.medias
      // this.id = window.localStorage.getItem('id')
    })
    .catch(e => {
      swal("ERROR", e.response.data, "error");
      this.errorEvent = e;
    })
    
  },
  methods: {
    switchToLogin(){
      window.location.href = "#/"
      location.reload()
    },
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
            swal("Success", "Event " + name + " successfully booked!", "success");
            
            this.bookedEvents = this.Account.events
          })
          .catch(e => {
            swal("Error", "Error Assigning Event to Account", "error")
            this.errorEvent = e;
          })
    },
    reserveItem: function (mediaID){
      AXIOS.put('/reserve_media_online/'.concat(mediaID), {}, {
        params: {
          userId: localStorage.getItem('id')
        }
      })
          .then(response => {
            swal("Success", "Media " + mediaID + " Reserved Successfully!", "success")
            this.checkedOutMedias = this.Account.medias
          })
          .catch(e => {
            swal("Error", "Error Reserving Media", "error")
            this.errorEvent = e;
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
            location.reload()
          })
          .catch(e => {
            swal("Error", "Error Updating Online Account", "error")
            this.errorEvent = e;
          })
    }
  }

  }

  