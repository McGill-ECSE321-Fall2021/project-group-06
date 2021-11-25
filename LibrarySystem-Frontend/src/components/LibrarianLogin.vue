<template>

  <div id="librarianlogin">
    <h1>Librarian Login</h1>
    <table>
      <tr v="Acconut">
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
          <button v-bind:disabled="false" @click="switchLogin()">I'm a User!</button>
        </td>
      </tr>
      <tr>
        <td >
          <button v-bind:disabled="false" @click="switchToOH()">View Opening Hours</button>
        </td>
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

  html{
    height: 100%;
  }
  body{
    background: #fff1e6
  }
</style>