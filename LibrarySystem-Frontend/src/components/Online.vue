<template>

  <div id="Online">
    <h1>User Online Account</h1>
      <div class="container">
    <div class="column">
    <table class="Account_Table">
      <tr v="Account">
        <th style="font-size:30px" colspan=2>Personal Information</th>
      </tr>
      <tr>
        <td class="Account_Table_left">
          <p style="font-size:20px">ID Number</p>
        </td>
        <td class="Account_Table_right">
          <p style="font-size:20px">{{ Account.id }} </p>
        </td>
      </tr>
      <tr>
        <td class="Account_Table_left">
          <p style="font-size:20px">Name</p>
        </td>
        <td class="Account_Table_right"> 
          <p style="font-size:20px">{{ Account.name }} </p>
        </td>
      </tr>
      <tr>
        <td class="Account_Table_left">
          <p style="font-size:20px">Local Account?</p>
        </td>
        <td class="Account_Table_right"> 
          <p style="font-size:20px">{{ Account.isLocal }} </p>
        </td>
      </tr>
      <tr>
        <td class="Account_Table_left">
          <p style="font-size:20px">Email Address</p>
        </td>
        <td class="Account_Table_right"> 
          <p style="font-size:20px">{{ Account.email }} </p>
        </td>        
      </tr>
      <tr>
        <td class="Account_Table_left">
          <p style="font-size:20px">Home Address</p>
        </td>
        <td class="Account_Table_right"> 
          <p style="font-size:20px">{{ Account.address }} </p>
        </td>
      </tr>
      <tr>
        <td class="Account_Table_left">
          <p style="font-size:20px">Number of Checked Out Media</p>
        </td>
        <td class="Account_Table_right"> 
          <p style="font-size:20px">{{ Account.numChecked }} </p>
        </td>
      </tr>
      <tr v-for="media in checkedOutMedias" :key="media.name">
        <td>
          {{name}}
        </td>
      </tr>
      <tr v-for="event in bookedEvent" :key="event.name">
        <td>
          {{event.name}}
        </td>
      </tr>
    </table>
    </div>
    <table class="UpdateAcc">
      <tr>
        <td>
          <p style="font-size:30px;font-weight:bold">
            Update Personal information
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <p style="font-size:20px">Update Account Name</p>
          <input type="text" v-model="onlineName" placeholder="New Name">
        </td>
      </tr>
      <tr>
        <td>
          <p style="font-size:20px">Update Password</p>
          <input type="text" v-model="onlinePassword" placeholder="New Password">
        </td>
      </tr>
      <tr>
        <td>
          <p style="font-size:20px">Update Home Address</p>
          <input type="text" v-model="onlineAddress" placeholder="New Home Address">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!onlineName || !onlinePassword || !onlineAddress" @click="updateInfo(onlineName,onlinePassword,onlineAddress)"> Update Personal Information </button>
        </td>
      </tr>
    </table>
    </div>
    <div class="column">
    <table class="Media">
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
  </div>
</template>
<script src="./js/BookEvent.js">
</script>
<style scoped>
/* @import '/assets/design.scss'; */
</style>
<style>
/* @import '/assets/design.scss'; */
  #Online {
    min-height: 800px;
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    /* background: #f2ece8; */
  }  
  table.Account_Table{
    min-height: 300px;
    min-width: 500px;
    max-width: 500px;
    text-align:center;
    /*float: left; */
    border-collapse: collapse;

    background: rgba(255, 255, 255, 0.06);
    border-radius: 16px;
    /*box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);*/
    backdrop-filter: blur(3.2px);
  }
  table.UpdateAcc{
    min-height: 300px;
    text-align:center;
    /*float: left; */
    border-spacing: 1px;
    border-collapse: separate;

    background: rgba(255, 255, 255, 0.06);
    border-radius: 16px;
    box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
    backdrop-filter: blur(3.2px);
  }
  td.Account_Table_left{
    border:1px solid black;
    padding-top: 10px;
    padding-bottom: 10px;
    padding-left: 10px;
    padding-right: 10px;
    vertical-align: middle;
    min-width: 50px;
    max-width: 50px;
  }
  td.Account_Table_right{
    border:1px solid black;
    padding-top: 10px;
    padding-bottom: 10px;
    padding-left: 10px;
    padding-right: 10px;
    vertical-align: middle;
    min-width: 100px;
    max-width: 100px;
  }
  td{
    padding-top: 10px;
    padding-bottom: 10px;
    padding-left: 10px;
    padding-right: 10px;
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