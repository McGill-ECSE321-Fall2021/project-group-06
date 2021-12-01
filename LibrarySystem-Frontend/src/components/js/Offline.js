/**
 * @author David Hu 
 * Offline.js Is how the Librarians and HeadLibrarians view Offline accounts
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
  name: 'Offline',
  data () {
    return {
        Account:'',
        offlineName: '',
        offlinePassword: '',
        onlineAddress: '',
        reserveMediaID: '',
        checkOutMediaID: '',
        media: '',
        offline: '',
        events: [],
        bookedEvents: [],
        medias: [],
        id: window.localStorage.getItem('offid'),
        name: '',
        
		    errorEvent: '',
      	response: []
    }
  },
  created: function () {
    AXIOS.get('/getOffline/'.concat(window.localStorage.getItem('offid')))
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
      window.localStorage.removeItem('offid')
      window.localStorage.removeItem('id')
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
            swal("Error", "Error Booking Event", "error")
            this.errorEvent = e;
          })
    },
    updateInfo: function (name, address){
      AXIOS.put('/edit_offline/'.concat(localStorage.getItem('id')), {}, {
        params: {
          address: address,
          name: name,
        }
      })
          .then(response => {
            swal("Success", "Account Updated Successfully!", "success")
            location.reload()
          })
          .catch(e => {
            swal("Error", "Error Updating Account", "error")
            this.errorEvent = e;
          })
    }
  }

  }

  