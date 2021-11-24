<template>

  <div id="Online">
     <table>
         ​<tr v-for-key="event in events">
     ​       <td>{{ event.name }}</td>
             ​<td>{{ event.eventDate }}</td>
            ​<td>{{ event.eventStart }}</td>
     ​        <td>{{ event.eventEnd }}</td>
          ​</tr>
        <tr v="account">
          <!-- <td  v-for="event in account.events" :key="event.name">
            {{event.name}}
          </td> -->
          
        <td>
              <input type="text" v-model="id" placeholder="Id">
              <input type="text" v-model="name" placeholder="Name">
              <button v-bind:disabled="!id || !name" @click="assignEvent(id, name)"> BookEvent </button>
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