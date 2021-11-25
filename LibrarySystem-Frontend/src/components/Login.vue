<template>
  <div id="login">
    <h1>User Login</h1>
    <table>
      <tr>
        <td>
          <p style="font-size:30px">
            Login
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <p>ID number</p>
          <input type="text" v-model="id" placeholder="ID number">
        </td>
      </tr>
      <tr>
        <td style="vertical-align: top;">
          <p>Password</p>
          <input type="text" v-model="password" placeholder="Password">
        </td>
      </tr>
      <tr>
        <td>
          <button id="center" v-bind:disabled="!id || !password" @click="login(id, password)">Login </button>
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td>
          <button v-bind:disabled="false" @click="switchLogin()">I'm a Librarian!</button>
        </td>
      </tr>
      <tr>
        <td >
          <button v-bind:disabled="false" @click="switchToOH()">View Opening Hours</button>
        </td>
      </tr>
    </table>
        <table>
      <tr>
        <td>
          <p style="font-size:30px">
            Register
          </p>
        </td>
      </tr>
      <tr>
        <td >
          <p>ID number:</p>
          <input type="text" v-model="newId" placeholder="ID number">
        </td>
      </tr>
      <tr>
        <td style="vertical-align: top;">
          <p>Password</p>
          <input type="text" v-model="newPassword" placeholder="Password">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!newId || !newPassword" @click="login(newId, newPassword)">Register </button>
        </td>
      </tr>
    </table>
      <p>
        <span v-if="errorLogin" style="color:red">Error: {{errorLogin}} </span>
      </p>
      <!-- <Offline :massage="massageForChild"> </Offline> -->
  </div>
  
</template>

<script src="./js/Login.js">
</script>
<style>
/* @import '/assets/design.css'; */
  #login {  
    min-height: 400px;
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
    /*Buttons */
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