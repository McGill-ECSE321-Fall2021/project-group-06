import axios from 'axios'
var config = require('../../../config')

var backendConfigurer = function(){
  switch(process.env.NODE_ENV){
      case 'development':
          return 'http://' + config.dev.backendHost + ':' + config.dev.backendPort;
      case 'production':
          return 'https://' + config.build.backendHost + ':' + config.build.backendPort ;
  }
};
var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = backendConfigurer();

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
  name: 'Login',
  data () {
    return {
			username: '',
			password: '',
			errorLogin: '',
			response: []
    }
  },
    methods: {
      something(){
        window.location.href = "#/Online"
        location.reload()
      },
      login (username, password) {
        AXIOS.get('/login/',$.param({username: username, password: password}))
        .then(response => {
          this.user = response.data
            this.type = this.user.userType
            window.localStorage.setItem('username', this.user.username)
            window.localStorage.setItem('type', this.type)
  
            if(this.type.localeCompare("customer")==0){
  
              window.location.href = "#/Online"
            }
            else{
              window.location.href = "/#/Offline"
            }
            location.reload();
        })
        .catch(e => {
  
          swal("ERROR", e.response.data, "error");
  
        })
      }
    }

  }

  