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
    name: 'Librarian',
    data () {
        return {
            librarian : '',
            id : '',
            librarianPassword : '',
            newOfflineID : '',
            newAddress : '',
            newName : '',
            updateOfflineID : '',
            updateAddress : '',
            updateName : '',
            deleteOfflineID : '',
            CheckOutOfflineID:'',
            CheckOutMediaOfflineID:'',
            CheckOutOfflineDate:'',
            CheckOutOnlineID:'',
            CheckOutMediaOnlineID:'',
            CheckOutOnlineDate:'',
            returnUserOnlineID:'',
            returnMediaOnlineID:'',
            returnOnlineDate:'',
            returnUserOfflineID:'',
            returnMediaOfflineID:'',
            returnOfflineDate:'',
            assignEventID:'',
            assignEventName:'',
            createEventName:'',
            createEventDate:'',
            createEventStartTime:'',
            createEventEndTime:'',
            updateEventName:'',
            updateEventDate:'',
            updateEventStartTime:'',
            updateEventEndTime:'',
            unReserveItemID:'',
            librarianError: '',
            response: []
        }
    },
    created: function () {
        AXIOS.get('/librarians/'.concat(localStorage.getItem('id')))
        .then(response => {
          this.librarian = response.data
          // this.id = window.localStorage.getItem('id')
        })
        .catch(e => {
            swal("ERROR", e.response.data, "error");
            this.errorEvent = e
        })
    },
    
    methods: {
        switchToHLMedia(){
            // window.localStorage.setItem('id', localStorage.getItem('id'))
            window.location.href = "#/MediaForHL"
            location.reload()
        },
        // switchToLogin(){
        //     window.location.href = "#/"
        //     location.reload()
        // },
        shiftToHeadLibrarian(){
            window.localStorage.setItem('id', localStorage.getItem('id'))
            window.location.href = "#/HeadLibrarian"
            location.reload()
        },
        switchToLogin(){
            window.localStorage.removeItem('id')
            window.localStorage.removeItem('lib')
            window.location.href = "#/"
            location.reload()
        },
        viewShift(){
            window.localStorage.setItem('id', localStorage.getItem('id'))
            window.location.href = "#/ViewShiftForHL"
            location.reload()
        },
        checkOutAnItemOnline: function (userID, mediaID, date2){
            AXIOS.put('/add_media_online/'.concat(userID), {}, {
                params: {
                    mediaId: mediaID
                }
            }),
            AXIOS.put('/edit_boolean/'.concat(mediaID), {}, {
                params: {
                    newIsCheckedOut: "true",
                    date: date2,
                    userID: userID
                }
            })
            .then(response => {
                swal("Success", "Item Checked Out Successfully!", "success")
              })
              .catch(e => {
                swal("ERROR", e.response.data, "error");
                this.librarianError = e
              })
        },
        checkOutAnItemOffline: function (userID, mediaID, date2){
            AXIOS.put('/add_media_offline/'.concat(userID), {}, {
                params: {
                    mediaId: mediaID
                }
            }),
            AXIOS.put('/edit_boolean/'.concat(mediaID), {}, {
                params: {
                    newIsCheckedOut: "true",
                    date: date2,
                    userID: userID
                }
            })
            .then(response => {
                swal("Success", "Item Checked Out Successfully!", "success")
              })
              .catch(e => {
                swal("ERROR", e.response.data, "error");
                this.librarianError = e
              })
        },
        returnAnItemOnline: function (userID, mediaID, date2){
            AXIOS.put('/return_media_online/'.concat(userID), {}, {
                params: {
                    mediaId: mediaID
                }
            }),
            AXIOS.put('/edit_boolean/'.concat(mediaID), {}, {
                params: {
                    newIsCheckedOut: "false",
                    date: date2,
                    userID: 0
                }
            })
            .then(response => {
                swal("Success", "Item Returned Successfully!", "success")
              })
              .catch(e => {
                swal("ERROR", e.response.data, "error");
                this.librarianError = e
              })
        },
        returnAnItemOffline: function (userID, mediaID, date2){
            AXIOS.put('/return_media_offline/'.concat(userID), {}, {
                params: {
                    mediaId: mediaID
                }
            }),
            AXIOS.put('/edit_boolean/'.concat(mediaID), {}, {
                params: {
                    newIsCheckedOut: "false",
                    date: date2,
                    userID: 0
                }
            })
            .then(response => {
                swal("Success", "Item Returned Successfully!", "success")
              })
              .catch(e => {
                swal("ERROR", e.response.data, "error");
                this.librarianError = e
              })
        },
        createOffline: function (newOfflineID, newAddress, newName){
            AXIOS.post('/createOfflineAccount/'.concat(newOfflineID), {}, {
                params: {
                    addr: newAddress,
                    name: newName
                }
            })
            .then(response => {
                swal("Success", "Account Created Successfully!", "success")
              })
              .catch(e => {
                swal("ERROR", e.response.data, "error");
                this.librarianError = e
              })
        },
        updateOffline: function (newOfflineID, newAddress, newName){
            AXIOS.put('/edit_offline/'.concat(newOfflineID), {}, {
                params: {
                    address: newAddress,
                    name: newName
                }
            })
            .then(response => {
                swal("Success", "Account Was Updated Successfully!", "success")
              })
              .catch(e => {
                swal("ERROR", e.response.data, "error");
                this.librarianError = e
              })
        },
        updatePW: function (password){
            AXIOS.put('/headLibrarian_UpdatePassword/'.concat(localStorage.getItem('id')), {}, {
                params: {
                    password: password
                }
            })
            .then(response => {
                swal("Success", "Password Updated Successfully!", "success")
              })
              .catch(e => {
                swal("ERROR", e.response.data, "error");
                this.librarianError = e
              })
        },
        deleteOffline: function (id){
            AXIOS.delete('/delete_offline/'.concat(id), {}, {
            })
            .then(response => {
                swal("Success", "Account Successfully Deleted!", "success")
              })
              .catch(e => {
                swal("ERROR", e.response.data, "error");
                this.librarianError = e
              })
        },
        createEvent: function (eventName, eventDate, eventStart, eventEnd){
            AXIOS.post('/create_event/'.concat(eventName), {}, {
                params: {
                    eventDate: eventDate,
                    eventStart: eventStart,
                    eventEnd: eventEnd
                }
            })
            .then(response => {
                swal("Success", "Event Created Successfully!", "success")
              })
              .catch(e => {
                swal("ERROR", e.response.data, "error");
                this.librarianError = e
              })
        },
        assignEvent: function (userID, name){
            AXIOS.put('/assignEvent/'.concat(userID), {}, {
                params: {
                    name: name
                }
            })
            .then(response => {
                swal("Success", "Event Assigned Successfully!", "success")
              })
              .catch(e => {
                swal("ERROR", e.response.data, "error");
                this.librarianError = e
              })
        },
        unassignEvent: function (userID, name){
            AXIOS.put('/unassignEvent/'.concat(userID), {}, {
                params: {
                    name: name
                }
            })
            .then(response => {
                swal("Success", "Event Unassigned Successfully!", "success")
              })
              .catch(e => {
                swal("ERROR", e.response.data, "error");
                this.librarianError = e
              })
        },
        deleteEvent: function (name){
            AXIOS.delete('/update_events/'.concat(name), {}, {
            })
            .then(response => {
                swal("Success", "Event Successfully Deleted!", "success")
              })
              .catch(e => {
                swal("ERROR", e.response.data, "error");
                this.librarianError = e
              })
        },
        unreserveItem: function (mediaID){
            AXIOS.put('/unreserveMedia/'.concat(mediaID), {}, {
              
            })
                .then(response => {
                  swal("Success", "Media " + mediaID + " Un-Reserved Successfully!", "success")
                  this.checkedOutMedias = this.Account.medias
                })
                .catch(e => {
                    swal("ERROR", e.response.data, "error");
                    this.errorEvent = e
                })
          }
    }
}