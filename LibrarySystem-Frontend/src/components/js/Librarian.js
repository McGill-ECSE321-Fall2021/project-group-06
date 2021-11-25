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
          this.errorEvent = e
        })
    },
    methods: {
        checkOutAnItem: function (userID, mediaID, date2){
            AXIOS.put('/add_media_online/'.concat(userID), {}, {
                params: {
                    mediaId: mediaID
                }
            }),
            AXIOS.put('/edit_boolean/'.concat(mediaID), {}, {
                params: {
                    newIsCheckedOut: "true",
                    date: date2
                }
            })
            .then(response => {
                swal("Success", "Item Checked Out Successfully!", "success")
              })
              .catch(e => {
                this.librarianError = e
              })
        },
        returnAnItem: function (userID, mediaID, date2){
            AXIOS.put('/return_media_online/'.concat(userID), {}, {
                params: {
                    mediaId: mediaID
                }
            }),
            AXIOS.put('/edit_boolean/'.concat(mediaID), {}, {
                params: {
                    newIsCheckedOut: "false",
                    date: date2
                }
            })
            .then(response => {
                swal("Success", "Item Returned Successfully!", "success")
              })
              .catch(e => {
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
                this.librarianError = e
              })
        },
        updatePW: function (password){
            AXIOS.post('/librarian_updatePassword/'.concat(localStorage.getItem('id')), {}, {
                params: {
                    password: password
                }
            })
            .then(response => {
                swal("Success", "Password Updated Successfully!", "success")
              })
              .catch(e => {
                this.librarianError = e
              })
        }
    }
}