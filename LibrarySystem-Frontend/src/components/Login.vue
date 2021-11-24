<template>

  <div id="login">
    <h2>User Login</h2>
    <table>
      <tr>
        <td>
          login
        </td>
      </tr>
      <tr>
        <td>
          <p>ID number</p>
          <input type="text" v-model="id" placeholder="ID number">
        </td>
      </tr>
      <tr>
        <td>
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
        <td>
          <button v-bind:disabled="false" @click="switchToOH()">View Opening Hours</button>
        </td>
      </tr>
    </table>
        <table>
      <tr>
        <td>
          Register
        </td>
      </tr>
      <tr>
        <td>
          <p >ID number:</p>
          <input type="text" v-model="newId" placeholder="ID number">
        </td>
      </tr>
      <tr>
        <td style="text-align:top">
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
  #login {
    min-height: 400px;
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    background: #f2ece8;
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
</style>