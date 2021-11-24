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
		errorLogin: '',
    	response: []
    }
  },
  
  methods: {

    bookEvent: function (id, name) {
      AXIOS.put('/assignEvent/'.concat(id), {
        params: {
          name: name
        }
      })
          .then(response => {
            this.Account = response.data

          })
          .catch(e => {
            this.errorLogin = e
          })
    }
  }

  }

  