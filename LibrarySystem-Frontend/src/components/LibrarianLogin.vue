<template>

  <div id="librarianlogin">
    <h2>Librarian Login</h2>
      <table>
        <tr v="Account">
          <td>{{ type }}</td>
        </tr>
    
      <tr>
          <td>
              <input type="text" v-model="id" placeholder="Username">
          </td>
          <td>
              <input type="text" v-model="password" placeholder="Password">
          </td>
      </tr>
      <tr>
          <button v-bind:disabled="!id" @click="login(id, password)">Login </button>
          <button v-bind:disabled="false" @click="switchLogin()">I'm an User!</button>
          <button v-bind:disabled="false" @click="switchToOH()">View Opening Hours</button>
      </tr>
      </table>
      <p>
        <span v-if="errorLogin" style="color:red">Error: {{errorLogin}} </span>
      </p>
  </div>
  
</template>

<script src="./js/LibrarianLogin.js">
</script>
<style>
  #librarianlogin {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    /* background: #f2ece8; */
  }
  #center{
    text-align: center;
  }
  table{
    min-height: 300px;
    width:33%;
    text-align:center;
    float: left;
  }
  td{
    width:33%;
    text-align:center;
  }
  p{
    margin-bottom:0px;
  }
  /* Buttons */
  button{
    color: #000000;
    border: none;
    background: #ff477e;
    padding: 20px 40px;
    border-radius: 100px;
    transition: all 0.5s eas-in-out;
  }
  button:disabled{
    color: #000000;
    background: #A29C9B;
    padding: 20px 40px;
    border-radius: 100px;
    transition: all 0.2s eas-in-out;
  }
  button:hover:enabled{
    color: #000000;
    box-shadow: 0px 0px 40px #ff0a54;
  }
  html{
    height: 100%;
  }
  body{
    background: #fff1e6
  }
</style>