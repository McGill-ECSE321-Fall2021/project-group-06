<template>
  <div id="login">
    <h1>User Login</h1>
     <div class="container">
    <div class="column">
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
    </div>
    <div class = "column">
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
      <tr>
        <td >
          <button v-bind:disabled="false" @click="switchToMedia()">View Medias</button>
        </td>
      </tr>
    </table>
    </div>
    <div class = "column">
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
        <td >
          <p>Name:</p>
          <input type="text" v-model="newName" placeholder="Name">
        </td>
      </tr>
      <tr>
        <td>
          <p>Address: </p>
          <input type="text" v-model="newAddress" placeholder="Address">
        </td>
      </tr>
      <tr>
        <td>
          <p>Local:</p>
          <select v-model="newIsLocal">
            <option value="True">local</option>
            <option value="False">not local</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>
          <p>Username:</p>
          <input type="text" v-model="newUsername" placeholder="Username">
        </td>
      </tr>
      <tr>
        <td>
          
          <p>Email:</p>
          <input type="text" v-model="newEmail" placeholder="Email">
        </td>
      </tr>
      <tr>
        <td style="vertical-align: top;">
          <p>Password:</p>
          <input type="text" v-model="newPassword" placeholder="Password">
        </td>
      </tr>
      <tr>
        <td>
          <button id = "center" v-bind:disabled="!newId || !newPassword || !newName || !newAddress || !newUsername || !newEmail" @click="createOnline(newId,newAddress,newName,newIsLocal,newUsername, newPassword, newEmail)">Register </button>
        </td>
      </tr>
    </table>
    </div>
      <p>
        <span v-if="errorLogin" style="color:red">Error: {{errorLogin}} </span>
      </p>
      <!-- <Offline :massage="massageForChild"> </Offline> -->
  </div>
  </div>
</template>

<script src="./js/Login.js">
</script>
<style scoped>
/* @import '/assets/design.scss'; */
</style>
<style>
/* @import '/assets/design.scss'; */
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
    /* width:33%; */
    text-align:center;
    /* float: left; */
    border-spacing: 15px;
    border-collapse: separate;

    background: rgba(255, 255, 255, 0.06);
    border-radius: 16px;
    box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
    backdrop-filter: blur(3.2px);
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
.column {
  float: left;
  vertical-align: 50%;
  width: 33%;
  padding: 10px;
}
.container {
  display: flex;
  justify-content: space-between;
}
.blank button{
  color: #fff1e600;
}
</style>