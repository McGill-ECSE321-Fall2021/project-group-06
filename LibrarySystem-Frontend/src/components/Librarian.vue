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
             view shift
             <!-- <button @click="deleteOffline(deleteOfflineID)"> view shift</button> -->
          </p>
        </td>
      </tr>
    </table>
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
