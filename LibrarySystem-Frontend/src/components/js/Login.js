import '../../assets/design.css'
import '../../assets/background.css'
import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})


export default {
  name: 'Login',
  data () {
    return {
      Account: '',
			id: '',
      password: '',
      newId: '',
      newName: '',
      newAddress: '',
      newIsLocal: '',
      newUsername: '',
      newEmail: '',
      newPassword: '',
      type: '',
			errorLogin: '',
			response: [],
    }
  },
  
  methods: {

    switchToMedia(){
      window.location.href = "#/Media"
      location.reload()
    },
    switchLogin(){
      window.location.href = "#/LibrarianLogin"
      location.reload()
    },
    switchToOH(){
      window.location.href = "#/OpeningHours"
      location.reload()
    },
    switchToEvent(){
      window.location.href = "#/Event"
      location.reload()
    },
    login: function (id, password) {
      AXIOS.get('/login/'.concat(id), {
        params: {
          password: password
        }
      })
          .then(response => {
          // JSON responses are automatically parsed.
            this.Account = response.data
            if(this.Account.accountCategory == "Online"){
              window.localStorage.setItem('id', this.id)
              window.location.href = "#/Online"
              location.reload()
            } else if (this.Account.accountCategory == "Offline") {
              window.location.href = "#/Media"
              location.reload()
            }
            //this.id = '727'
          })
          .catch(e => {
            swal("ERROR", e.response.data, "error");
          })
    },
    createOnline: function (userID, address, name, isLocal, username, password, email){
      AXIOS.post('/onlines/'.concat(userID), {}, {
          params: {
              address: address,
              name: name,
              accountCategory: "Online",
              isLocal: isLocal,
              numChecked: "0",
              username: username,
              password: password,
              email: email
          }
      })
        .then(response => {
          swal("Success", "Account Created Successfully! Please Login", "success")
        })
        .catch(e => {
          swal("ERROR", e.response.data, "error");
        })
    }
  }

  }

  