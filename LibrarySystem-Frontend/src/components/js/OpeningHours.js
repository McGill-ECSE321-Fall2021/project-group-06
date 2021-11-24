import axios from 'axios'
// var config = require('../../config')
var config = require('../../../config')


var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})


function OpeningHourDto (name, date, start, end) {
    this.name = name
    this.eventDate = date
    this.startTime = start
    this.endTime = end
  }
  export default {
    name: 'openingHour',
    data () {
      return {
        openingHours: [],
        newOH: '',
        errorOH: '',
        response: []
      }
    },
    created: function () {
        AXIOS.get('/openingHours')
        .then(response => {
          // JSON responses are automatically parsed.
          this.openingHours = response.data
        })
        .catch(e => {
          this.errorOH = e
        })
    
    },
    methods: {
        createOH: function (oHName) {
            AXIOS.post('/openingHours/'.concat(oHName), {}, {})
            .then(response => {
            // JSON responses are automatically parsed.
              this.openingHours.push(response.data)
              this.errorOH = ''
              this.newOH = ''
            })
            .catch(e => {
              var errorMsg = e.response.data.message
              console.log(errorMsg)
              this.errorOH = errorMsg
            })
        }
    }
}