<template>

  <div id="login">
    <h2>Persons</h2>
      <table>
        <!-- <tr v-for="person in persons" :key="person.name" >
            <td>{{ person.name }}</td>
            <td>
              <ul>
                <li v-for="event in person.events" :key="event.name">
                  {{event.name}}
                </li>
              </ul>
            </td>
        </tr> -->
    <table>
<tr>
    <td>
        <input v-model="username" placeholder="Username">
    </td>
    <td>
        <input type="text" v-model="password" placeholder="Password">
    </td>
</tr>
<tr>
  <p>Message is: {{ username }}</p>
  <p>Message is: {{ password }}</p>
    <button v-bind:disabled="!username || !password" @click="login(username, password)">Login </button>
</tr>
</table>
    </table>
    <p>
      <span v-if="!username" style="color:red">YOU FUCKING RETARD</span>
    </p>
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