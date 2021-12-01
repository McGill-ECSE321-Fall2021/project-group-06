<template>
    <div id="librarian">
    <h2>
      Librarian Menu
    </h2>
    <div>
    <button class="centerpage" @click="switchToLogin()"> Sign Out</button>
    <button class="centerpage" @click="switchToMedia()"> View Media</button>
    </div>
    <table class="leftThird">
      <tr>
        <td>
          <p style="font-size:30px">
             View Shift(s)
          </p>
          <p>
             <button @click="viewShift()"> View Shift(s)</button>
          </p>
        </td>
      </tr>
    </table>
    <table class="middleThird">
      <tr v="Librarian">
        <td>
          <p style="font-size:30px">
            Account Information: {{ librarian.id }}
          </p>
        </td>
      </tr>
      <tr>
        <td>
          
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
          <input type="text" v-model="librarianPassword" placeholder="New Password">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || !librarianPassword" @click="updatePW(librarianPassword)"> Update Account Information </button>
        </td>
      </tr>
    </table>
    <table class="first4">
      <tr>
        <td>
          <p style="font-size:30px">
            Make Offline Account
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="newOfflineID" placeholder="ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="newAddress" placeholder="Address">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="newName" placeholder="Name">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || !newOfflineID || !newAddress || !newName" @click="createOffline(newOfflineID, newAddress, newName)"> Create New Offline User </button>
        </td>
      </tr>
    </table>
    <table class="second4">
      <tr>
        <td>
          <p style="font-size:30px">
            Update Offline Account
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateOfflineID" placeholder="ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateAddress" placeholder="New Address">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateName" placeholder="New Name">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || !updateOfflineID || !updateAddress || !updateName" @click="updateOffline(updateOfflineID, updateAddress, updateName)"> Update Offline User</button>
        </td>
      </tr>
    </table>
    <table class="third4">
      <tr>
        <td>
          <p style="font-size:30px">
            Delete Offline Account
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="deleteOfflineID" placeholder="ID to be Deleted">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || !deleteOfflineID" @click="deleteOffline(deleteOfflineID)"> Delete Offline User</button>
        </td>
      </tr>
    </table>
    <table class="fourth4">
      <tr>
        <td>
          <p style="font-size:30px">
            Lookup Offline
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="offlineIDtoget" placeholder="ID">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!offlineIDtoget" @click="switchToOfflineAccountToGet"> Lookup Offline User</button>
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
          <input type="text" v-model="CheckOutOfflineID" placeholder="Offline Account ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="CheckOutMediaOfflineID" placeholder="Media ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="CheckOutOfflineDate" placeholder="Check Out Date">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || !CheckOutOfflineID || !CheckOutMediaOfflineID || !CheckOutOfflineDate" @click="checkOutAnItemOffline(CheckOutOfflineID,CheckOutMediaOfflineID,CheckOutOfflineDate)"> Check Out Item</button>
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
          <input type="text" v-model="CheckOutOnlineID" placeholder="Online Account ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="CheckOutMediaOnlineID" placeholder="Media ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="CheckOutOnlineDate" placeholder="Check Out Date">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || !CheckOutOnlineID || !CheckOutMediaOnlineID || !CheckOutOnlineDate" @click="checkOutAnItemOnline(CheckOutOnlineID,CheckOutMediaOnlineID,CheckOutOnlineDate)"> Check Out Item</button>
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
          <input type="text" v-model="returnUserOfflineID" placeholder="Offline Account ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="returnMediaOfflineID" placeholder="Media ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="returnOfflineDate" placeholder="Enter Return Date">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || !returnUserOfflineID || !returnMediaOfflineID || !returnOfflineDate" @click="returnAnItemOffline(returnUserOfflineID,returnMediaOfflineID,returnOfflineDate)"> Return Item</button>
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
          <input type="text" v-model="returnUserOnlineID" placeholder="Online Account ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="returnMediaOnlineID" placeholder="Media ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="returnOnlineDate" placeholder="Enter Return Date">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || !returnUserOnlineID || !returnMediaOnlineID || !returnOnlineDate" @click="returnAnItemOnline(returnUserOnlineID,returnMediaOnlineID,returnOnlineDate)"> Return Item</button>
        </td>
      </tr>
    </table>
    <table class="center">
      <tr>
        <td>
          <!-- <p style="font-size:30px"> -->
          Unreserve Item
          <!-- </p> -->
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="unReserveItemID" placeholder="ID">
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
          <p style="font-size:30px">
          Create Event
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createEventName" placeholder="Name">
          
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createEventDate" placeholder="Date">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createEventStartTime" placeholder="Start Time">
          
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createEventEndTime" placeholder="End Time">
         
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || !createEventName || !createEventDate || !createEventStartTime || !createEventEndTime" @click="createEvent(createEventName,createEventDate,createEventStartTime,createEventEndTime)"> Create Event</button>
        </td>
      </tr>
    </table>
    <table class="middleThird">
      <tr>
        <td>
          <p style="font-size:30px">
          Assign Event to Account
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="assignEventID" placeholder="Account ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="assignEventName" placeholder="Event Name">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || !assignEventID || !assignEventName" @click="assignEvent(assignEventID,assignEventName)"> Assign Event</button>
        </td>
      </tr>
    </table>
    <table class='rightThird'>
      <tr>
        <td>
          <p style="font-size:30px">
          Update Event
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateEventName" placeholder="Name">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateEventDate" placeholder="New Date">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateEventStartTime" placeholder="New Start Time">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateEventEndTime" placeholder="New End Time">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || !updateEventName || !updateEventDate || !updateEventStartTime || !updateEventEndTime" @click="updateEvent(updateEventName,updateEventDate,updateEventStartTime,updateEventEndTime)"> Update Event</button>
        </td>
      </tr>
    </table>
    <table class = 'leftHalf'>
      <tr>
        <td>
          <p style="font-size:30px">
          Unassign Event From Account
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="unassignEventID" placeholder="Account ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="unassignEventName" placeholder="Event Name">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || !unassignEventID || !unassignEventName" @click="unassignEvent(unassignEventID,unassignEventName)"> Unassign Event</button>
        </td>
      </tr>
    </table>
    <table class = 'rightHalf'>
      <tr>
        <td>
          <p style="font-size:30px">
          Delete Event
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="deleteEventName" placeholder="Event Name To Be Deleted">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || !deleteEventName" @click="deleteEvent(deleteEventName)"> Delete Event</button>
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
  .first4{
    width: 25%;
    height:300px;
    float: left
  }
  .second4{
    width: 25%;
    height:300px;
    float: left
  }
  .third4{
    width: 25%;
    height:300px;
    float: left
  }
  .fourth4{
    width: 25%;
    height: 300px;
    float: left
  }
</style>
