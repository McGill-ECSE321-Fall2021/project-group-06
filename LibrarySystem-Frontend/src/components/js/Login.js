import axios from 'axios'
import Offline from "@/components/Offline.vue"
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
      type: '',
      // name: '',
			// password: '',
			errorLogin: '',
			response: [],
      massageForChild: "my dick fell off"
    }
  },
  components: {
    Offline
  },
  
  methods: {

    switchLogin(){
      window.location.href = "#/LibrarianLogin"
      location.reload()
    },
    switchToOH(){
      window.location.href = "#/OpeningHours"
      location.reload()
    },
    login: function (username, password) {
      AXIOS.get('/login/'.concat(username), {
        params: {
          password: password
        }
      })
          .then(response => {
          // JSON responses are automatically parsed.
            this.Account = response.data
            if(this.Account.accountCategory == "Online"){
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

  