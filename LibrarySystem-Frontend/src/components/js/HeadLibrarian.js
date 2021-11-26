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
        librarians: [],
        currLib: '',
        //HL
        headLibrarians: [],
        hireLibrarianID:'',
        hireLibrarianPassword:'',
        assignLibrarianID: '',
        unAssignLibrarianID:'',
        id: '',
        pwd: '',
        errorHL: '',
        //Shift
        shifts: [],
        createShiftID:'',
        createDayOfWeek:'',
        createStartTime:'',
        createEndTime:'',
        updateShiftID:'',
        updateDayOfWeek:'',
        updateStartTime:'',
        updateEndTime:'',
        assignShiftID:'',
        unAssignShiftID:'',
        deleteShiftID:'',
        shiftID: '',
        dayOfWeek: '',
        startTime: '',
        endTime: '',
        //OH
        deleteOpeningHourID:'',
        createOpeningHourID: '',
        createOpeningHourDayOfWeek:'',
        createOpeningHourStartTime:'',
        createOpeningHourEndTime:'',
        updateOpeningHourID: '',
        updateOpeningHourDayOfWeek:'',
        updateOpeningHourStartTime:'',
        updateOpeningHourEndTime:'',
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
        //COI
        createCOIID:'',
        createCOIName:'',
        createCOIType:'',
        createCOIDate:'',
        updateCOIID:'',
        updateCOIName:'',
        updateCOIType:'',
        updateCOICheckedOut:'',
        updateCOIReserved:'',
        updateCOIDate:'',
        deleteCOIID:'',
        fireLibrarianID: '',
        //NCOI
        createNCOIID:'',
        createNCOIType:'',
        createNCOIName:'',
        updateNCOIID:'',
        updateNCOIType:'',
        updateNCOIName:'',
        deleteNCOIID:'',
        updateCOIBorrowinPeriod:'',
        errorOH: '',
        response: []
      }
    },
    created: function () {
      this.currLib = window.localStorage.getItem('lib')
      AXIOS.get('/librarians/')
        .then(response => {
          this.librarians = response.data
        })
        .catch(e => {
          this.errorOH = e
        }),
      AXIOS.get('/getShifts/')
      .then(response => {
        this.shifts = response.data
      })
      .catch(e => {
        this.errorOH = e
      })
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
      switchToLibrarian(){
        // window.localStorage.setItem('id', this.id)
        window.location.href = "#/LibrarianForHL"
        location.reload()
      },
      switchToLogin(){
        window.localStorage.removeItem('id')
        window.localStorage.removeItem('lib')
        window.location.href = "#/"
        location.reload()
      },
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
      updateShift: function(shiftID, dayOfWeek, startTime, endTime){
        AXIOS.put('/updateShift/'.concat(shiftID), {}, {
          params: {
            DayOfWeek: dayOfWeek,
            start: startTime,
            end: endTime
          }
        })
        .then(response => {
          swal("Success", "Shift Successfully Updated!", "success")
        })
        .catch(e => {
          this.librarianError = e
        })
      },
      deleteShift: function (id){
        AXIOS.delete('/deleteShift/'.concat(id), {}, {
        })
        .then(response => {
            swal("Success", "Shift Successfully Deleted!", "success")
          })
          .catch(e => {
            this.librarianError = e
          })
      },
      assignShift: function (shiftID, libID){
        AXIOS.put('/assignShift/'.concat(libID), {}, {
          params:{
            shiftID: shiftID
          }
        })
        .then(response => {
            swal("Success", "Shift " + shiftID + " Successfully Assigned to " + libID + " !" , "success")
          })
          .catch(e => {
            this.librarianError = e
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
      },
      updateOH: function (ohID, dayOfWeek, startTime, endTime) {
        AXIOS.put('/updateOpeningHour/'.concat(ohID), {}, {
          params: {
            newDay: dayOfWeek,
            newStart: startTime,
            newEnd: endTime
          }
        })
        .then(response => {
          swal("Success", "Opening Hour Successfully Updated!", "success")
        })
        .catch(e => {
          this.librarianError = e
        })
      },
      unAssignShift: function (id, shiftID) {
        AXIOS.put('/unassignShift/'.concat(id), {}, {
          params: {
            shiftID: shiftID
          }
        })
        .then(response => {
          swal("Success", "Shift Successfully Unassigned!", "success")
        })
        .catch(e => {
          this.librarianError = e
        })
      },
      deleteOpeningHour: function (id){
        AXIOS.delete('/deleteOpeningHour/'.concat(id), {}, {
        })
        .then(response => {
            swal("Success", "Opening Hour Successfully Deleted!", "success")
          })
          .catch(e => {
            this.librarianError = e
          })
      },
      hireLibrarian: function(libID, password){
        AXIOS.post('/hireLibrarian/'.concat(libID), {}, {
          params: {
            pwd: password
          }
        })
        .then(response => {
          swal("Success", "Librarian Was Successfully Hired!", "success")
        })
        .catch(e => {
          this.errorShift = e
        })
      },
      fireLibrarian: function (id){
        AXIOS.delete('/fireLibrarian/'.concat(id), {}, {
        })
        .then(response => {
            swal("Success", "Librarian Successfully Fired!", "success")
          })
          .catch(e => {
            this.librarianError = e
          })
      },
      //media, coi
      createCOI: function(mediaID, mediaType, name, borrowingPeriod, date){
        AXIOS.post('/create_checkOutItems/'.concat(mediaID), {}, {
          params: {
            mediaType: mediaType,
            name: name,
            isCheckedOut: "false",
            isReserved: "false",
            borrowingPeriod: borrowingPeriod,
            startDateString: date
          }
        })
        .then(response => {
          swal("Success", "Check Out Item Was Created Successfully!", "success")
        })
        .catch(e => {
          this.errorShift = e
        })
      },
      updateCOI: function(mediaID, mediaType, name, checkedOut, reserved, borrowingPeriod, date){
        AXIOS.put('/edit_checkOutItems/'.concat(mediaID), {}, {
          params: {
            newMediaType: mediaType,
            newName: name,
            newIsCheckedOut: checkedOut,
            newIsReserved: reserved,
            newBorrowingPeriod: borrowingPeriod,
            date: date
          }
        })
        .then(response => {
          swal("Success", "Check Out Item Successfully Updated!", "success")
        })
        .catch(e => {
          this.librarianError = e
        })
      },
      deleteCOI: function (id){
        AXIOS.delete('/delete_CheckOutItem/'.concat(id), {}, {
        })
        .then(response => {
            swal("Success", "Check Out Item Successfully Deleted!", "success")
          })
          .catch(e => {
            this.librarianError = e
          })
      },
      //NCOI
      createNCOI: function(mediaID, mediaType, name){
        AXIOS.post('/create_nonCheckOutItems/'.concat(mediaID), {}, {
          params: {
            mediaType: mediaType,
            name: name
          }
        })
        .then(response => {
          swal("Success", "Non Check Out Item Was Created Successfully!", "success")
        })
        .catch(e => {
          this.errorShift = e
        })
      },
      updateNCOI: function(mediaID, mediaType, name){
        AXIOS.put('/edit_nonCheckOutItems/'.concat(mediaID), {}, {
          params: {
            newMediaType: mediaType,
            newMediaName: name,
          
          }
        })
        .then(response => {
          swal("Success", "Non Check Out Item Successfully Updated!", "success")
        })
        .catch(e => {
          this.librarianError = e
        })
      },
      deleteNCOI: function (id){
        AXIOS.delete('/delete_nonCheckOutItem/'.concat(id), {}, {
        })
        .then(response => {
            swal("Success", "Non Check Out Item Successfully Deleted!", "success")
          })
          .catch(e => {
            this.librarianError = e
          })
      }

    }
}