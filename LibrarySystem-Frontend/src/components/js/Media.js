/**
 * @author David Hu 
 * Media.js is how the users will view the media. Different from MediaForHeadLibrarians
 * and MediaForLibrarians since they can see who reserved and who borrowed each item.
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
    name: 'Media',
    data () {
      return {
          checkOutItems: [],
          nonCheckOutItems: [],
          errorEvent: '',
          response: []
      }
    },
    created: function () {
        AXIOS.get('/checkOutItems/')
        .then(response => {
          this.checkOutItems = response.data
          // this.id = window.localStorage.getItem('id')
        })
        .catch(e => {
          swal("ERROR", e.response.data, "error");
          this.errorEvent = e
        }),
        AXIOS.get('/nonCheckOutItems/')
        .then(response => {
          this.nonCheckOutItems = response.data
          // this.id = window.localStorage.getItem('id')
        })
        .catch(e => {
          swal("ERROR", e.response.data, "error");
          this.errorEvent = e
        })
    },
    
    methods: {
      switchToLogin(){
        window.location.href = "#/"
        location.reload()
      },
    }
  
}