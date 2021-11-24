<template>

  <div id="login">
    <h2>User Login</h2>
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
          <button v-bind:disabled="false" @click="switchLogin()">I'm a Librarian!</button>
          <button v-bind:disabled="false" @click="switchToOH()">View Opening Hours</button>
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
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    background: #f2ece8;
  }
</style>