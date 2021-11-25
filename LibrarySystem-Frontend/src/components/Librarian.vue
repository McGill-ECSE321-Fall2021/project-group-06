<template>
    <div id="librarian">
    <table>
      <tr v="Librarian">
        <td>
          <p style="font-size:30px">
            Personal information
          </p>
        </td>
      </tr>
      <tr>
        <td>
          {{ librarian.id }}
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td>
          <p style="font-size:30px">
            Update Password
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="librarianPassword" placeholder="update online password">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!librarianPassword" @click="updatePW(librarianPassword)"> update personal information </button>
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td>
          <p style="font-size:30px">
            Make Offline Account
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="newOfflineID" placeholder="new OfflineID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="newAddress" placeholder="new Address">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="newName" placeholder="new Name">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!newOfflineID || !newAddress || !newName" @click="createOffline(newOfflineID, newAddress, newName)"> create new offline user </button>
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td>
          <p style="font-size:30px">
            update Offline account
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateOfflineID" placeholder="update OfflineID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateAddress" placeholder="update Address">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateName" placeholder="update Name">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!updateOfflineID || !updateAddress || !updateName" @click="updateOffline(updateOfflineID, updateAddress, updateName)"> update Offline user</button>
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td>
          <p style="font-size:30px">
            delete Offline
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="deleteOfflineID" placeholder="update OfflineID">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!deleteOfflineID" @click="deleteOffline(deleteOfflineID)"> delete Offline user</button>
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td>
          <p style="font-size:30px">
            check out an Item Online User
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="CheckOutOnlineID" placeholder="Online user ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="CheckOutMediaOnlineID" placeholder="media id">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="CheckOutOnlineDate" placeholder="check out date">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!CheckOutOnlineID || !CheckOutMediaOnlineID || !CheckOutOnlineDate" @click="checkOutAnItemOnline(CheckOutOnlineID,CheckOutMediaOnlineID,CheckOutOnlineDate)"> Check out item</button>
        </td>
      </tr>
    </table>
        <table>
      <tr>
        <td>
          <p style="font-size:30px">
            check out an Item Offline User
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="CheckOutOfflineID" placeholder="Offline user ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="CheckOutMediaOfflineID" placeholder="media id">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="CheckOutOfflineDate" placeholder="check out date">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!CheckOutOfflineID || !CheckOutMediaOfflineID || !CheckOutOfflineDate" @click="checkOutAnItemOffline(CheckOutOfflineID,CheckOutMediaOfflineID,CheckOutOfflineDate)"> Check out item</button>
        </td>
      </tr>
    </table>
        <table>
      <tr>
        <td>
          <p style="font-size:30px">
            return an Item Online User
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="returnUserOnlineID" placeholder="Online user ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="returnMediaOnlineID" placeholder="media id">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="returnOnlineDate" placeholder="check out date">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!returnUserOnlineID || !returnMediaOnlineID || !returnOnlineDate" @click="returnAnItemOnline(returnUserOnlineID,returnMediaOnlineID,returnOnlineDate)"> return online user item</button>
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td>
          <p style="font-size:30px">
            return an Item Offline User
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="returnUserOfflineID" placeholder="Offline user ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="returnMediaOfflineID" placeholder="media id">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="returnOfflineDate" placeholder="check out date">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!returnUserOfflineID || !returnMediaOfflineID || !returnOfflineDate" @click="returnAnItemOffline(returnUserOfflineID,returnMediaOfflineID,returnOfflineDate)"> return user offline item</button>
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td>
          <p style="font-size:30px">
             view shift
             <button @click="viewShift()"> view shift</button>
          </p>
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td>
          assign Event
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="assignEventID" placeholder="set id">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="assignEventName" placeholder="set Name">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!assignEventID || !assignEventName" @click="assignEvent(assignEventID,assignEventName)"> assign event</button>
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td>
          create event
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createEventName" placeholder="set Name">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createEventDate" placeholder="set date">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createEventStartTime" placeholder="set start time">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createEventEndTime" placeholder="set end time">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!createEventName || !createEventDate || !createEventStartTime || !createEventEndTime" @click="createEvent(createEventName,createEventDate,createEvent,createStartTime,createEventEndTime)"> create event</button>
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td>
          update event
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateEventName" placeholder="set Name">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateEventDate" placeholder="set date">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateEventStartTime" placeholder="set start time">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateEventEndTime" placeholder="set end time">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!updateEventName || !updateEventDate || !updateEventStartTime || !updateEventEndTime" @click="updateEvent(updateEventName,updateEventDate,updateEventStartTime,updateEventEndTime)"> update event</button>
        </td>
      </tr>
    </table>
    <button @click="switchToLogin()"> sign out</button>
    <p>
      <span v-if="librarianError" style="color:red">Error: {{librarianError}} </span>
    </p>
    </div>
</template>
<script src="./js/librarian.js">
</script>
<style>
  #librarian {
    min-height: 600px;
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
