<template>

  <div id="Online">
    <input type="text" v-model="id" placeholder="Id">
    <input type="text" v-model="name" placeholder="Name">
    <button v-bind:disabled="!id && !name" @click="assignEvent(id, name)"> BookEvent </button>
     <table>

        <tr v="account" >
          <td>
            <ul>
              <li v-for-key="event in account.events">
                {{event.name}}
              </li>
            </ul>
          </td>
        </tr>
    </table>
  </div>
</template>
<script src="./js/BookEvent.js">
</script>
<style>
  #Online {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    background: #f2ece8;
  }
</style>