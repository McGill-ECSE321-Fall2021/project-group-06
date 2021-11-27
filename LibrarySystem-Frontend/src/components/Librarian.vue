<template>
    <div id="librarian">
    <h2>
      Librarian menu
    </h2>
    <div>
    <button class="centerpage" @click="switchToLogin()"> sign out</button>
    <button class="centerpage" @click="switchToMedia()"> view Media</button>
    </div>
    <table class="leftThird">
      <tr>
        <td>
          <p style="font-size:30px">
             View shift(s)
          </p>
          <p>
             <button @click="viewShift()"> View shift(s)</button>
          </p>
        </td>
      </tr>
    </table>
    <table class="middleThird">
      <tr v="Librarian">
        <td>
          <p style="font-size:30px">
            Account Information
          </p>
        </td>
      </tr>
      <tr>
        <td>
          {{ librarian.id }}
        </td>
      </tr>
    </table>
    <table class="rightThird">
      <tr>
        <td>
          <p style="font-size:30px">
            Update Password
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="librarianPassword" placeholder="Set new password">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || !librarianPassword" @click="updatePW(librarianPassword)"> Update account information </button>
        </td>
      </tr>
    </table>
    <table class="leftThird">
      <tr>
        <td>
          <p style="font-size:30px">
            Make Offline Account
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="newOfflineID" placeholder="Set OfflineID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="newAddress" placeholder="Enter Address">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="newName" placeholder="Enter Name">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || !newOfflineID || !newAddress || !newName" @click="createOffline(newOfflineID, newAddress, newName)"> Create new Offline user </button>
        </td>
      </tr>
    </table>
    <table class="middleThird">
      <tr>
        <td>
          <p style="font-size:30px">
            Update Offline Account
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateOfflineID" placeholder="Enter OfflineID to be updated">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateAddress" placeholder="Enter new Address">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateName" placeholder="Enter new Name">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || !updateOfflineID || !updateAddress || !updateName" @click="updateOffline(updateOfflineID, updateAddress, updateName)"> Update Offline user</button>
        </td>
      </tr>
    </table>
    <table class="rightThird">
      <tr>
        <td>
          <p style="font-size:30px">
            Delete Offline Account
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="deleteOfflineID" placeholder="Enter OfflineID to be deleted">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || !deleteOfflineID" @click="deleteOffline(deleteOfflineID)"> Delete Offline user</button>
        </td>
      </tr>
    </table>
    <table class="leftHalf">
      <tr>
        <td>
          <p style="font-size:30px">
            Check out an Item for Offline User
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="CheckOutOfflineID" placeholder="Enter OfflineID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="CheckOutMediaOfflineID" placeholder="Enter MediaID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="CheckOutOfflineDate" placeholder="Enter Checked out date">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || !CheckOutOfflineID || !CheckOutMediaOfflineID || !CheckOutOfflineDate" @click="checkOutAnItemOffline(CheckOutOfflineID,CheckOutMediaOfflineID,CheckOutOfflineDate)"> Check out item</button>
        </td>
      </tr>
    </table>
    <table class="rightHalf">
      <tr>
        <td>
          <p style="font-size:30px">
            Check out an Item for Online User
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="CheckOutOnlineID" placeholder="Enter OnlineID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="CheckOutMediaOnlineID" placeholder="Enter MediaID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="CheckOutOnlineDate" placeholder="Enter Checked out date">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || !CheckOutOnlineID || !CheckOutMediaOnlineID || !CheckOutOnlineDate" @click="checkOutAnItemOnline(CheckOutOnlineID,CheckOutMediaOnlineID,CheckOutOnlineDate)"> Check out item</button>
        </td>
      </tr>
    </table>
    <table class="leftHalf">
      <tr>
        <td>
          <p style="font-size:30px">
            Return an Item for Offline User
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="returnUserOfflineID" placeholder="Enter OfflineID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="returnMediaOfflineID" placeholder="Enter MediaID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="returnOfflineDate" placeholder="Enter Check out date">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || !returnUserOfflineID || !returnMediaOfflineID || !returnOfflineDate" @click="returnAnItemOffline(returnUserOfflineID,returnMediaOfflineID,returnOfflineDate)"> Return item</button>
        </td>
      </tr>
    </table>
    <table class="rightHalf">
      <tr>
        <td>
          <p style="font-size:30px">
            Return an Item for Online User
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="returnUserOnlineID" placeholder="Enter OnlineID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="returnMediaOnlineID" placeholder="Enter MediaID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="returnOnlineDate" placeholder="Enter Check out date">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || !returnUserOnlineID || !returnMediaOnlineID || !returnOnlineDate" @click="returnAnItemOnline(returnUserOnlineID,returnMediaOnlineID,returnOnlineDate)"> Return item</button>
        </td>
      </tr>
    </table>
    <table class="center">
      <tr>
        <td>
          Unreserve Item
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="unReserveItemID" placeholder="Enter MediaID">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!unReserveItemID" @click="unreserveItem(unReserveItemID)"> Unreserve Item</button>
        </td>
      </tr>
    </table>
    <table class="leftThird">
      <tr>
        <td>
          Create event
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createEventName" placeholder="Set Name">
          {{ createEventName }}
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createEventDate" placeholder="Set date">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createEventStartTime" placeholder="Set start time">
          {{ createEventStartTime }}
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createEventEndTime" placeholder="Set end time">
          {{ createEventEndTime }}
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || !createEventName || !createEventDate || !createEventStartTime || !createEventEndTime" @click="createEvent(createEventName,createEventDate,createEventStartTime,createEventEndTime)"> Create event</button>
        </td>
      </tr>
    </table>
    <table class="middleThird">
      <tr>
        <td>
          Assign Event to Account
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="assignEventID" placeholder="Enter ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="assignEventName" placeholder="Enter Event Name">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || !assignEventID || !assignEventName" @click="assignEvent(assignEventID,assignEventName)"> Assign event</button>
        </td>
      </tr>
    </table>
    <table class='rightThird'>
      <tr>
        <td>
          Update event
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateEventName" placeholder="Enter Name">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateEventDate" placeholder="Set new date">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateEventStartTime" placeholder="Set new start time">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateEventEndTime" placeholder="Set new end time">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || !updateEventName || !updateEventDate || !updateEventStartTime || !updateEventEndTime" @click="updateEvent(updateEventName,updateEventDate,updateEventStartTime,updateEventEndTime)"> Update event</button>
        </td>
      </tr>
    </table>
    <p>
      <span v-if="librarianError" style="color:red">Error: {{librarianError}} </span>
    </p>
    </div>
</template>
<script src="./js/Librarian.js">
</script>
<style>
  #librarian {
    min-height: 600px;
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
  }
  body{
    background: #fff1e6;
  }
  table{
    min-height: 350px;
    min-width: 300px;
    text-align:center;
    /*float: left;*/
    border-spacing: 15px;
    border-collapse: seperate;

    background: rgba(255, 255, 255, 0.06);
    border-radius: 16px;
    box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
    backdrop-filter: blur(3.2px);
    margin-top: 10px;
    margin-bottom: 10px;
  }
  .center{
    margin-left: auto;
    margin-right: auto;
  }
  .centerpage {
    margin-left: auto;
    margin-right: auto;
  }
  .leftHalf{
    width: 50%;
    float:left
  }
  .rightHalf{
    width:50%;
    float:left
  }
  .leftThird{
    width:33%;
    float:left
  }
  .rightThird{
    width: 33%;
    float: left
  }
  .middleThird{
    width: 33%;
    float:left
  }
</style>
