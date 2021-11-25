<template>
  <div id="openinghours">
    <h2>Head Librarian</h2>
    <table>
      <tr>
        <td>
          Monday: {{ Monday.startTime }} to {{ Monday.endTime }} ID: {{ Monday.id }}
        </td>
      </tr>
      <tr>
        <td>
          Tuesday: {{ Tuesday.startTime }} to {{ Tuesday.endTime }} ID: {{ Tuesday.id }}
        </td>
      </tr>
      <tr>
        <td>
          Wednesday: {{ Wednesday.startTime }} to {{ Wednesday.endTime }} ID: {{ Wednesday.id }}
        </td>
      </tr>
      <tr>
        <td>
          Thursday: {{ Thursday.startTime }} to {{ Thursday.endTime }} ID: {{ Thursday.id }}
        </td>
      </tr>
      <tr>
        <td>
          Friday: {{ Friday.startTime }} to {{ Friday.endTime }} ID: {{ Friday.id }}
        </td>
      </tr>
      <tr>
        <td>
          Saturday : Closed
        </td>
      </tr>
      <tr>
        <td>
          Sunday : Closed
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td>
          create a shift
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createShiftID" placeholder="set ID for shift">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createDayOfWeek" placeholder="set day of week for shift">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createStartTime" placeholder="set start time">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createEndTime" placeholder="set end time">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!createShiftID || !createDayOfWeek || !createStartTime || !createEndTime" @click="createShift(createShiftID,createDayOfWeek,createStartTime,createEndTime)"> create a shift</button>
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td>
          update a shift
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateShiftID" placeholder="set ID for shift">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateDayOfWeek" placeholder="set day of week for shift">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateStartTime" placeholder="set start time">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateEndTime" placeholder="set end time">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!updateShiftID || !updateDayOfWeek || !updateStartTime || !updateEndTime" @click="updateShift(updateShiftID,updateDayOfWeek,updateStartTime,updateEndTime)"> update a shift</button>
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td>
          delete a shift
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="deleteShiftID" placeholder="set shift id to be deleted">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!deleteShiftID" @click="deleteShift(deleteShiftID)"> delete shift</button>
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td>
          assign shift to librarian
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="assignShiftID" placeholder="assign shift ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="assignLibrarianID" placeholder="Id of librarian to be assigned">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!assignShiftID || !assignLibrarianID" @click="assignShift(assignShiftID,assignLibrarianID)"> assign shift</button>
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td>
          unassign shift to librarian
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="unAssignShiftID" placeholder="set shift id">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="unAssignLibrarianID" placeholder="set librarian id">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!unAssignShiftID || !unAssignLibrarianID" @click="unAssignShift(unAssignShiftID,unAssignLibrarianID)"> unAssign shift</button>
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td>
          create opening hour
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createOpeningHourID" placeholder="set id">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createOpeningHourDayOfWeek" placeholder="set day of week">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createOpeningHourStartTime" placeholder="set start time">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createOpeningHourEndTime" placeholder="set end time">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!createOpeningHourID || !createOpeningHourDayOfWeek || !createOpeningHourStartTime || !createOpeningHourEndTime" @click="createOH(createOpeningHourID,createOpeningHourDayOfWeek,createOpeningHourStartTime,createOpeningHourEndTime)"> create opening hour</button>
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td>
          update opening hour
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateOpeningHourID" placeholder="update id">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateOpeningHourDayOfWeek" placeholder="update day of week">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateOpeningHourStartTime" placeholder="update start time">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateOpeningHourEndTime" placeholder="update end time">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!updateOpeningHourID || !updateOpeningHourDayOfWeek || !updateOpeningHourStartTime || !updateOpeningHourEndTime" @click="updateOH(updateOpeningHourID,updateOpeningHourDayOfWeek,updateOpeningHourStartTime,updateOpeningHourEndTime)"> update opening hour</button>
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td>
          delete opening hour
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="deleteOpeningHourID" placeholder="id to delete">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!deleteOpeningHourID" @click="deleteOpeningHour(deleteOpeningHourID)"> delete opening hour</button>
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td>
          hire a librarian
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="hireLibrarianID" placeholder="set id">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="hireLibrarianPassword" placeholder="set password">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!hireLibrarianID || !hireLibrarianPassword" @click="hireLibrarian(hireLibrarianID,hireLibrarianPassword)"> hire librarian</button>
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td>
          fire a ibrarian
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="fireLibrarianID" placeholder="set id">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!fireLibrarianID" @click="fireLibrarian(fireLibrarianID)"> fire librarian</button>
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td>
          create COI
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createCOIID" placeholder="set id">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createCOIName" placeholder="set name">
        </td>
      </tr>
      <tr>
        <td>
          <p>media type:</p>
          <select v-model="createCOIType">
            <option value="Book">book</option>
            <option value="Movie">movie</option>
            <option value="Music">music</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createCOIBorrowingPeriod" placeholder="set borrowing period">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createCOIDate" placeholder="set date">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!createCOIID || !createCOIName || !createCOIType || !createCOIBorrowingPeriod|| !createCOIDate" @click="createCOI(createCOIID,createCOIType,createCOIName,createCOIBorrowingPeriod,createCOIDate)"> create COI</button>
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td>
          update COI
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateCOIID" placeholder="update id">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateCOIName" placeholder="update name">
        </td>
      </tr>
      <tr>
        <td>
          <p>media type:</p>
          <select v-model="updateCOIType">
            <option value="Book">book</option>
            <option value="Movie">movie</option>
            <option value="Music">music</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>
          <p>is checked out?:</p>
          <select v-model="updateCOICheckedOut">
            <option value="True">True</option>
            <option value="False">False</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>
          <p>is reserved:</p>
          <select v-model="updateCOIReserved">
            <option value="True">True</option>
            <option value="False">False</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateCOIBorrowingPeriod" placeholder="update borrowing period">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateCOIDate" placeholder="update date">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!updateCOIID || !updateCOIName || !updateCOIType || !updateCOICheckedOut ||!updateCOIReserved || !updateCOIBorrowingPeriod|| !updateCOIDate" @click="updateCOI(updateCOIID,updateCOIType,updateCOIName,updateCOICheckedOut,updateCOIReserved,updateCOIBorrowingPeriod,updateCOIDate)"> update COI</button>
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td>
          delete COI
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="deleteCOIID" placeholder="set id">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!deleteCOIID" @click="deleteCOI(deleteCOIID)"> delete COI</button>
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td>
          create NCOI
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createNCOIID" placeholder="set id">
        </td>
      </tr>
      <tr>
        <td>
          <p>media type:</p>
          <select v-model="createNCOIType">
            <option value="Newspaper">newspaper</option>
            <option value="Archive">archive</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createNCOIName" placeholder="set name">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!createNCOIID || !createNCOIType || !createNCOIName" @click="createNCOI(createNCOIID,createNCOIType,createNCOIName)"> create NCOI</button>
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td>
          update NCOI
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateNCOIID" placeholder="set id">
        </td>
      </tr>
      <tr>
        <td>
          <p>media type:</p>
          <select v-model="updateNCOIType">
            <option value="Newspaper">newspaper</option>
            <option value="Archive">archive</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateNCOIName" placeholder="set name">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!updateNCOIID || !updateNCOIType || !updateNCOIName" @click="updateNCOI(updateNCOIID,updateNCOIType,updateNCOIName)"> update NCOI</button>
        </td>
      </tr>
    </table>
    <table>
      <tr>
        <td>
          delete NCOI
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="deleteNCOIID" placeholder="set id">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!deleteNCOIID" @click="deleteNCOI(deleteNCOIID)"> delete NCOI</button>
        </td>
      </tr>
    </table>
    <button  @click="switchToLibrarian()"> switch to librarian options</button>
    <button  @click="switchToLogin()"> sign out</button>
    <p>
        <span v-if="errorOH" style="color:red">Error: {{errorOH}} </span>
    </p>
  </div>
</template>

<script src="./js/HeadLibrarian.js">
</script>


<style scoped>
  #openinghours {
    min-height: 600px;
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    background: #f2ece8;
  }  table{
    min-height: 300px;
    text-align:center;
    float: left;
  }
</style>
