import '../../assets/design.css'
import '../../assets/background.css'
import axios from 'axios'
var config = require('../../../config')

// var backendConfigurer = function(){
//   switch(process.env.NODE_ENV){
//       case 'development':
//           return 'http://' + config.dev.backendHost + ':' + config.dev.backendPort;
//       case 'production':
//           return 'https://' + config.build.backendHost + ':' + config.build.backendPort ;
//   }
// };
var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort
// var backendUrl = backendConfigurer();

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
              this.type = "vagina"
            }
            //this.id = '727'
          })
          .catch(e => {
            this.errorLogin = e
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
          this.librarianError = e
        })
    }
    
    // login (username, password) {
    //   AXIOS.get('/login/',$.param({username: username, password: password}))
    //   .then(response => {
    //     this.user = response.data
    //       this.type = this.user.userType
    //       window.localStorage.setItem('username', this.user.username)
    //       window.localStorage.setItem('type', this.type)

    //       if(this.type.localeCompare("customer")==0){

    //         window.location.href = "#/Online"
    //       }
    //       else{
    //         window.location.href = "/#/Offline"
    //       }
    //       location.reload();
    //   })
    //   .catch(e => {

    //     swal("ERROR", e.response.data, "error");

    //   })
    // }
  }

  }

  