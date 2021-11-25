import axios from 'axios'
// var config = require('../../config')
var config = require('../../../config')


var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'headLibrarian',
    data () {
      return {
        //HL
        headLibrarians: [],
        id: "",
        pwd: "",
        errorHL: "",
        //Shift
        shifts: [],
        shiftID: "",
        dayOfWeek: '',
        startTime: '',
        endTime: '',
        //OH
        openingHours: [],
        Monday: '',
        Tuesday: '',
        Wednesday: '',
        Thursday: '',
        Friday: '',
        id: '',
        DayOfWeek: '',
        startTime: '',
        endTime: '',
        errorOH: '',
        response: []
      }
    },
    created: function () {
        AXIOS.get('/openingHours')
        .then(response => {
          // JSON responses are automatically parsed.
          this.openingHours = response.data
          for (let x in response.data){
            if(response.data[x].dayOfWeek == "Monday"){
              this.Monday = response.data[x];
            }
          }
          for (let x in response.data){
            if(response.data[x].dayOfWeek == "Tuesday"){
              this.Tuesday = response.data[x];
            }
          }
          for (let x in response.data){
            if(response.data[x].dayOfWeek == "Wednesday"){
              this.Wednesday = response.data[x];
            }
          }
          for (let x in response.data){
            if(response.data[x].dayOfWeek == "Thursday"){
              this.Thursday = response.data[x];
            }
          }
          for (let x in response.data){
            if(response.data[x].dayOfWeek == "Friday"){
              this.Friday = response.data[x];
            }
          }
        })
        .catch(e => {
          this.errorOH = e
        })
    
    },
    methods: {
      // createHL: function(id, pwd){
      //   AXIOS.post('/create_headlibrarian', '/create_headlibrarian/', {
      //     params: {
      //       id: id,
      //       pwd: pwd,
      //     }
      //   })
      //   .then(response => {
      //     this.headLibrarians.push(response.data)
      //     this.errorHL = ''
      //     this.newHL = ''
      //   })
      //   .catch(e => {
      //     this.erroHL = e
      //   })
      // },
      createShift: function(shiftID, dayOfWeek, startTime, endTime){
        AXIOS.post('/createShift/'.concat(shiftID), {}, {
          params: {
            DayOfWeek: dayOfWeek,
            start: startTime,
            end: endTime,
          }
        })
        .then(response => {
          swal("Success", "Shift Was Created Successfully!", "success")
        })
        .catch(e => {
          this.errorShift = e
        })
      },
      createOH: function (id,DayOfWeek,startTime,endTime) {
          AXIOS.post('/create_openingHour/'.concat(id), {}, {
            params: {
              dayOfWeek: DayOfWeek,
              startTime: startTime,
              endTime: endTime,
            }
          })
          .then(response => {
          // JSON responses are automatically parsed.

          //@niels i think this whole thing could just be empty cause you are getting all of OpeningHours in ur created function above
          //not 100% sure, just keep it like this
          // This whole thing bad, lol -David
            swal("Success", "Opening Hour Was Created Successfully!", "success")
          })
          .catch(e => {
            this.errorOH = e
          })
      }
    }
}