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
          <button v-bind:disabled="!onlineName || !onlinePassword || !onlineAddress" @click="updateInfo(onlineName,onlinePassword,onlineAddress)"> update personal information </button>
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
          <button v-bind:disabled="!reserveMediaID" @click="reserveItem(reserveMediaID)"> reserve media </button>
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
    <div id="scrollBox">
      <table border=1 frame=hsides rules=rows style="width:100%">
        <tr v-for="media in medias" :key="media.name">
          {{ media.name }}
        </tr>
      </table>
    </div>
    <div id="scrollBox">
      <table border=1 frame=hsides rules=rows style="width:100%">
        <tr v-for="event in events" :key="event.name">
          {{ event.name }}
        </tr>
      </table>
    </div>
    <p>
      <span v-if="errorEvent" style="color:red">Error: {{errorEvent}} </span>
    </p>
  </div>
</template>
<script src="./js/BookEvent.js">
</script>
<style>
  #Online {
    min-height: 800px;
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    background: #f2ece8;
  }  
  table{
    min-height: 300px;
    text-align:center;
    float: left;
  }
#scrollBox {
  border: 1px solid black;
  width: 300px;
  height: 200px;
  overflow: scroll;
  float: left;
}
</style>