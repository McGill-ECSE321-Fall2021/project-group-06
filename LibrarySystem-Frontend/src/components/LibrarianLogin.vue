<template>

  <div id="librarianlogin">
    <h1>Librarian Login</h1>
    <div class="container">
    <div class="column">
    <table>
      <tr v="Account">
        <td>
          <p style="font-size:30px">
            Login
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <p>ID Number</p>
          <input type="text" v-model="id" placeholder="ID Number">
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
          <button id="center" v-bind:disabled="!id || !password" @click="login(id, password)">Login</button>
        </td>
      </tr>
    </table>
    </div>
    <div class = "column">
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
      <tr>
        <td >
        </td>
      </tr>
    </table>
      <p>
        <span v-if="errorLogin" style="color:red">Error: {{errorLogin}} </span>
      </p>
  </div>
    <div class = "column">
    </div>
    </div>
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
    /* width:33%; */
    text-align:center;
    /* float: left; */
    border-spacing: 15px;
    border-collapse: separate;

    background: rgba(255, 255, 255, 0.06);
    border-radius: 16px;
    box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
    backdrop-filter: blur(3.2px);
    empty-cells: show;
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

</style>