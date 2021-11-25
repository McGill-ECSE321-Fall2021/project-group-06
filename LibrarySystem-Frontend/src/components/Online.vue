<template>

  <div id="Online">
    <table>
      <tr v="Account">
        <td>
          <p style="font-size:30px">
            Personal information
          </p>
        </td>
      </tr>
      <tr>
        <td>
          {{ Account.id }}
        </td>
      </tr>
      <tr>
        <td>
          {{ Account.name }}
        </td>
      </tr>
      <tr>
        <td>
          {{ Account.isLocal }}
        </td>
      </tr>
      <tr>
        <td>
          {{ Account.numChecked }}
        </td>
      </tr>
      <tr v-for="event in bookedEvent" :key="event.name">
        <td>
          {{event.name}}
        </td>
      </tr>
      <tr v-for="media in checkedOutMedias" :key="media.name">
        <td>
          {{name}}
        </td>
      </tr>
      <tr>
        <td>
          {{ Account.address }}
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
    <div id="scrollBox">
      <table border=1 frame=hsides rules=rows style="width:100%">
        <tr v-for="media in medias" :key="media.mediaName">
          
            {{ media.mediaName }} {{ media.mediaID }}
          
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
    <button @click="switchToLogin()"> sign out</button>
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
    /* background: #f2ece8; */
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
  html{
    height: 100%;
  }
  body{
    background: #fff1e6
  }
</style>