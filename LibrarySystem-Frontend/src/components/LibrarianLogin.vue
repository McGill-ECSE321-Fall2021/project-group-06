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
    background: #f2ece8;
  }
</style>