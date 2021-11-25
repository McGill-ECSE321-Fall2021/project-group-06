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
            librarianError: '',
            response: []
        }
    },
    created: function () {
        
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
        createOffline: function (userID, address, name){
            AXIOS.post('/createOfflineAccount/'.concat(userID), {}, {
                params: {
                    addr: address,
                    name: name
                }
            })
            .then(response => {
                swal("Success", "Account Created Successfully!", "success")
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