<template>
  <div id="openinghours">
    <h2>Head Librarian menu</h2>
    <div>
    <button  @click="switchToLibrarian()"> Switch To Librarian Services</button>
    <button  @click="switchToLogin()"> Sign Out</button>
    </div>
    <table class="fourth">
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
    <table class="fourth">
      <tr>
        <td>
          Create Opening Hour
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createOpeningHourID" placeholder="Set ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createOpeningHourDayOfWeek" placeholder="Set Day Of Week">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createOpeningHourStartTime" placeholder="Set Start Time">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createOpeningHourEndTime" placeholder="Set End Time">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || currLib!=1  || !createOpeningHourID || !createOpeningHourDayOfWeek || !createOpeningHourStartTime || !createOpeningHourEndTime" @click="createOH(createOpeningHourID,createOpeningHourDayOfWeek,createOpeningHourStartTime,createOpeningHourEndTime)"> Create Opening Hour</button>
        </td>
      </tr>
    </table>
    <table class="fourth">
      <tr>
        <td>
          Update Opening Hour
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateOpeningHourID" placeholder="Enter ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateOpeningHourDayOfWeek" placeholder="Set New Day Of Week">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateOpeningHourStartTime" placeholder="Set New Start Time">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateOpeningHourEndTime" placeholder="Set New End Time">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || currLib!=1  || !updateOpeningHourID || !updateOpeningHourDayOfWeek || !updateOpeningHourStartTime || !updateOpeningHourEndTime" @click="updateOH(updateOpeningHourID,updateOpeningHourDayOfWeek,updateOpeningHourStartTime,updateOpeningHourEndTime)"> Update Opening Hour</button>
        </td>
      </tr>
    </table>
    <table class="fourth">
      <tr>
        <td>
          Delete Opening Hour
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="deleteOpeningHourID" placeholder="Enter ID To Be Deleted">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || currLib!=1 || !deleteOpeningHourID" @click="deleteOpeningHour(deleteOpeningHourID)"> Delete Opening Hour</button>
        </td>
      </tr>
    </table>
    <table class="half">
      <tr>
        <td>
          Hire A Librarian
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="hireLibrarianID" placeholder="Set ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="hireLibrarianPassword" placeholder="Set Password">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || currLib!=1 || !hireLibrarianID || !hireLibrarianPassword" @click="hireLibrarian(hireLibrarianID,hireLibrarianPassword)"> Hire Librarian</button>
        </td>
      </tr>
    </table>
    <table class="half">
      <tr>
        <td>
          Fire A Librarian
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="fireLibrarianID" placeholder="Enter ID">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || currLib!=1 || !fireLibrarianID" @click="fireLibrarian(fireLibrarianID)"> Fire Librarian</button>
        </td>
      </tr>
    </table>
    <table class="third">
      <tr>
        <td>
          Create A Shift
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createShiftID" placeholder="Set ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createDayOfWeek" placeholder="Set Day Of Week">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createStartTime" placeholder="Set Start Time">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createEndTime" placeholder="Set End Time">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || currLib!=1 ||!createShiftID || !createDayOfWeek || !createStartTime || !createEndTime" @click="createShift(createShiftID,createDayOfWeek,createStartTime,createEndTime)"> Create Shift</button>
        </td>
      </tr>
    </table>
    <table class="third">
      <tr>
        <td>
          Update A Shift
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateShiftID" placeholder="Enter ShiftID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateDayOfWeek" placeholder="Set New Day Of Week">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateStartTime" placeholder="Set New Start Time">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateEndTime" placeholder="Set New End Time">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || currLib!=1 || !updateShiftID || !updateDayOfWeek || !updateStartTime || !updateEndTime" @click="updateShift(updateShiftID,updateDayOfWeek,updateStartTime,updateEndTime)"> Update Shift</button>
        </td>
      </tr>
    </table>
    <table class="third">
      <tr>
        <td>
          Delete A Shift
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="deleteShiftID" placeholder="Enter ShiftID To Be Deleted">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || currLib!=1 || !deleteShiftID" @click="deleteShift(deleteShiftID)"> Delete Shift</button>
        </td>
      </tr>
    </table>
    <table class="half">
      <tr>
        <td>
          Assign Shift To Librarian
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="assignShiftID" placeholder="Enter ShiftID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="assignLibrarianID" placeholder="Enter LibrarianID">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || currLib!=1  || !assignShiftID || !assignLibrarianID" @click="assignShift(assignShiftID,assignLibrarianID)"> Assign Shift</button>
        </td>
      </tr>
    </table>
    <table class="half">
      <tr>
        <td>
          Unassign Shift To Librarian
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="unAssignShiftID" placeholder="Enter ShiftID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="unAssignLibrarianID" placeholder="Enter LibrarianID">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || currLib!=1  || !unAssignShiftID || !unAssignLibrarianID" @click="unAssignShift(unAssignShiftID,unAssignLibrarianID)"> Unassign Shift</button>
        </td>
      </tr>
    </table>
    <table class="third COI">
      <tr>
        <td>
          Create COI
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createCOIID" placeholder="Set ItemID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createCOIName" placeholder="Set Item Title">
        </td>
      </tr>
      <tr>
        <td>
          <p>media type:</p>
          <select v-model="createCOIType">
            <option value="Book">Book</option>
            <option value="Movie">Novie</option>
            <option value="Music">Music</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createCOIBorrowingPeriod" placeholder="Set Borrowing Period">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createCOIDate" placeholder="Set Date">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || currLib!=1 || !createCOIID || !createCOIName || !createCOIType || !createCOIBorrowingPeriod|| !createCOIDate" @click="createCOI(createCOIID,createCOIType,createCOIName,createCOIBorrowingPeriod,createCOIDate)"> Create COI</button>
        </td>
      </tr>
    </table>
    <table class="third COI">
      <tr>
        <td>
          Update COI
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateCOIID" placeholder="Enter ItemID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateCOIName" placeholder="Enter new title">
        </td>
      </tr>
      <tr>
        <td>
          <p>media type:</p>
          <select v-model="updateCOIType">
            <option value="Book">Book</option>
            <option value="Movie">Movie</option>
            <option value="Music">Music</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>
          <p>Is Checked Out?</p>
          <select v-model="updateCOICheckedOut">
            <option value="True">True</option>
            <option value="False">False</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>
          <p>Is Reserved?</p>
          <select v-model="updateCOIReserved">
            <option value="True">True</option>
            <option value="False">False</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateCOIBorrowingPeriod" placeholder="Set New Borrowing Period">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateCOIDate" placeholder="Set New Date">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || currLib!=1 || !updateCOIID || !updateCOIName || !updateCOIType || !updateCOICheckedOut ||!updateCOIReserved || !updateCOIBorrowingPeriod|| !updateCOIDate" @click="updateCOI(updateCOIID,updateCOIType,updateCOIName,updateCOICheckedOut,updateCOIReserved,updateCOIBorrowingPeriod,updateCOIDate)"> Update COI</button>
        </td>
      </tr>
    </table>
    <table class="third COI">
      <tr>
        <td>
          Delete COI
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="deleteCOIID" placeholder="Enter ItemID">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || currLib!=1  || !deleteCOIID" @click="deleteCOI(deleteCOIID)"> Delete COI</button>
        </td>
      </tr>
    </table>
    <table class="third">
      <tr>
        <td>
          Create NCOI
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createNCOIID" placeholder="Set ItemID">
        </td>
      </tr>
      <tr>
        <td>
          <p>Media Type:</p>
          <select v-model="createNCOIType">
            <option value="Newspaper">Newspaper</option>
            <option value="Archive">Archive</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createNCOIName" placeholder="Set Item Title">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || currLib!=1  || !createNCOIID || !createNCOIType || !createNCOIName" @click="createNCOI(createNCOIID,createNCOIType,createNCOIName)"> Create NCOI</button>
        </td>
      </tr>
    </table>
    <table class="third">
      <tr>
        <td>
          Update NCOI
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateNCOIID" placeholder="Enter ItemID">
        </td>
      </tr>
      <tr>
        <td>
          <p>Media Type:</p>
          <select v-model="updateNCOIType">
            <option value="Newspaper">Newspaper</option>
            <option value="Archive">Archive</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateNCOIName" placeholder="Enter New Title">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || currLib!=1  || !updateNCOIID || !updateNCOIType || !updateNCOIName" @click="updateNCOI(updateNCOIID,updateNCOIType,updateNCOIName)"> Update NCOI</button>
        </td>
      </tr>
    </table>
    <table class="third">
      <tr>
        <td>
          Delete NCOI
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="deleteNCOIID" placeholder="Enter ItemID">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || currLib!=1 || !deleteNCOIID" @click="deleteNCOI(deleteNCOIID)"> Delete NCOI</button>
        </td>
      </tr>
    </table>
    <div>
    <table class="half">
      View All Librarians
      <tr v-for="librarian in librarians" :key="librarian.id">
        <td>
          {{librarian.id}}
        </td>
      </tr>
    </table>
    </div>
    <div>
    <table class="half">
      View All Shifts
      <tr v-for="shift in shifts" :key="shift.id">
        <td>
          {{shift.shiftID}}
          {{shift.dayOfWeek}}
          {{shift.startTime}}
          {{shift.endTime}}
        </td>
      </tr>
    </table>
    </div>
    <p>
        <span v-if="errorOH" style="color:red">Error: {{errorOH}} </span>
    </p>
  </div>
</template>

<script src="./js/HeadLibrarian.js">
</script>


<style>
  #openinghours {
    min-height: 600px;
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
  }  
  body{
    background: #fff1e6;
  }
  table{
    min-height: 400px;
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
  .fourth {
    width:25%;
    float:left
  }
  .half{
    width:50%;
    float:left
  }
  .third{
    width:33%;
    float:left
  }
  .COI{
    min-height: 650px;
  }
  .centerpage{
    margin-left: auto;
    margin-right: auto;
    min-width: 300px;
    min-height: 150px
  }
  .centerpage tr:hover{
    background-color: rgba(109, 88, 88, 0.5)
  }
</style>
