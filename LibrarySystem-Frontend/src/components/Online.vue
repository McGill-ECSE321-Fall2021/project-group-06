<template>

  <div id="Online">
     <table>
         ​<tr v-for="event in events" :key="event.name">
            <td>
            {{ event.name }}
            {{ event.eventDate }}
            {{ event.eventStart }}
            {{ event.endTime }}
            </td>
          ​</tr>
        <tr v="account">
          <td  v-for="event in bookedEvents" :key="event.name">
            {{event.name}}
          </td>
          
        <td >
          <p>{{ id }} </p>
              <input type="text" v-model="name" placeholder="Name">
              <button v-bind:disabled="!name" @click="bookEvent(id, name)"> BookEvent </button>
        </td>
        </tr>
    </table>
    <p>
        <span v-if="errorEvent" style="color:red">Error: {{errorEvent}} </span>
    </p>
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