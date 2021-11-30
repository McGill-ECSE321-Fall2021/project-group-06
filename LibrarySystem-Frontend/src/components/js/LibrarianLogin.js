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
        type: '',
        // name: '',
		// password: '',
		errorLogin: '',
		response: []
    }
  },
  
  methods: {
    
    switchLogin(){
      window.location.href = "#/"
      location.reload()
    },
    switchToOH(){
        window.location.href = "#/OpeningHours"
        location.reload()
      },
    login: function (username, password) {
      AXIOS.get('/librLogin/'.concat(username), {
        params: {
          pwd: password
        }
      })
          .then(response => {
          // JSON responses are automatically parsed.
            this.Account = response.data
            if(this.Account.hl){
                window.localStorage.setItem('id', this.id)
                window.localStorage.setItem('lib', 1)
                window.location.href = "#/HeadLibrarian"
                location.reload()
            } else {
                window.localStorage.setItem('id', this.id)
                window.localStorage.setItem('lib', 2)
                window.location.href = "#/Librarian"
                location.reload()
            }
            
            //this.id = '727'
          })
          .catch(e => {
            swal("ERROR", e.response.data, "error");
            this.errorLogin = e
          })
    }
  }

  }

  