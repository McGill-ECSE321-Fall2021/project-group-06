<template>

  <div id="Online">
    <table>
      <tr v="online">
        <td>
          <p style="font-size:30px">
            Personal information
          </p>
        </td>
      </tr>
      <tr>
        <td>
          VARIABLE_TO_CHANGE ONLINE ID
        </td>
      </tr>
      <tr>
        <td>
          VARIABLE_TO_CHANGE ONLINE NAME
        </td>
      </tr>
      <tr>
        <td>
          VARIABLE_TO_CHANGE ONLINE ISLOCAL
        </td>
      </tr>
      <tr>
        <td>
          VARIABLE_TO_CHANGE ONLINE NUMCHECKED
        </td>
      </tr>
      <tr>
        <td>
          VARIABLE_TO_CHANGE ONLINE EVENTS
        </td>
      </tr>
      <tr>
        <td>
          VARIABLE_TO_CHANGE ONLINE MEDIAS
        </td>
      </tr>
      <tr>
        <td>
          VARIABLE_TO_CHANGE ONLINE PASSWORD
        </td>
      </tr>
      <tr>
        <td>
          VARIABLE_TO_CHANGE ONLINE ADDRESS
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td>
          <p style="font-size:30px">
            Update Personal information
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="onlineName" placeholder="update online name">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="onlinePassword" placeholder="update online password">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="onlineAddress" placeholder="update online address">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!onlineName || !onlinePassword || !onlineAddress" @click="VARIABLE_TO_CHANGE(onlineName,onlinePassword,onlineAddress)"> update personal information </button>
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td>
          <p style="font-size:30px">
            Reserve Media
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="reserveMediaID" placeholder="mediaID">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!reserveMediaID" @click="VARIABLE_TO_CHANGE(reserveMediaID)"> reserve media </button>
        </td>
      </tr>
    </table>
       <table>
      <tr>
        <td>
          <p style="font-size:30px">
            Checkout an Item
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="checkOutMediaID" placeholder="media ID">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!checkOutMediaID" @click="VARIABLE_TO_CHANGE(checkOutMediaID)"> checkout this item </button>
        </td>
      </tr>
    </table>
     <!-- <table>
      <tr v="Account">
          <td>{{ id }}</td>
      </tr>
         ​<tr v-for="event in events" :key="event.name">
            <td>
            {{ event.name }}
            {{ event.eventDate }}
            {{ event.eventStart }}
            {{ event.endTime }}
            </td>
          ​</tr>
        <tr>
          <td  v-for="event in bookedEvents" :key="event.name">
            {{event.name}}
          </td>
        </tr>
        <td >
          <input type="text" v-model="name" placeholder="Name">
          <button v-bind:disabled="!name" @click="bookEvent(name)"> BookEvent </button>
        </td>
        <td>
          <input type="text" v-model="mediaID" placeholder="media ID">
          <button v-bind:disabled="!mediaID" @click="findMedia(mediaID)"> mediaID </button>
        </td>
        <td>
          we need:
          online information
          update online information
          delete Account
          return media
          reserve media
          check media
          get media
          checkout media

          book event

        </td>
        <td>
          <tr v="media">
            <td>
              {{ media.mediaID }}
              {{ media.mediaType }}
              {{ media.name }}
            </td>
          </tr>
        </td>
    </table> -->
    <p>
      <span v-if="errorEvent" style="color:red">Error: {{errorEvent}} </span>
    </p>
  </div>
</template>
<script>
</script>
<style>
  #Online {
    min-height: 400px;
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    background: #f2ece8;
  }  
  table{
    min-height: 300px;
    text-align:center;
    float: left;
  }
</style>