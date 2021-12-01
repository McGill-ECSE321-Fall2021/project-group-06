<template>
  <div id="openinghours">
    <h2>Head Librarian menu</h2>
    <div>
    <button  @click="switchToLibrarian()"> switch to Librarian services</button>
    <button  @click="switchToLogin()"> sign out</button>
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
          <p style="font-size:30px">
          Create opening hour
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createOpeningHourID" placeholder="ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createOpeningHourDayOfWeek" placeholder="Day of Week">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createOpeningHourStartTime" placeholder="Start Time">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createOpeningHourEndTime" placeholder="End Time">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || currLib!=1  || !createOpeningHourID || !createOpeningHourDayOfWeek || !createOpeningHourStartTime || !createOpeningHourEndTime" @click="createOH(createOpeningHourID,createOpeningHourDayOfWeek,createOpeningHourStartTime,createOpeningHourEndTime)"> Create opening hour</button>
        </td>
      </tr>
    </table>
    <table class="fourth">
      <tr>
        <td>
          <p style="font-size:30px">
          Update opening hour
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateOpeningHourID" placeholder="ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateOpeningHourDayOfWeek" placeholder="New Day of Week">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateOpeningHourStartTime" placeholder="New Start Time">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateOpeningHourEndTime" placeholder="New End Time">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || currLib!=1  || !updateOpeningHourID || !updateOpeningHourDayOfWeek || !updateOpeningHourStartTime || !updateOpeningHourEndTime" @click="updateOH(updateOpeningHourID,updateOpeningHourDayOfWeek,updateOpeningHourStartTime,updateOpeningHourEndTime)"> Update opening hour</button>
        </td>
      </tr>
    </table>
    <table class="fourth">
      <tr>
        <td>
          <p style="font-size:30px">
          Delete opening hour
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="deleteOpeningHourID" placeholder="ID to be deleted">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || currLib!=1 || !deleteOpeningHourID" @click="deleteOpeningHour(deleteOpeningHourID)"> Delete opening hour</button>
        </td>
      </tr>
    </table>
    <table class="half">
      <tr>
        <td>
          <p style="font-size:30px">
          Hire a librarian
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="hireLibrarianID" placeholder="ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="hireLibrarianPassword" placeholder="Password">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || currLib!=1 || !hireLibrarianID || !hireLibrarianPassword" @click="hireLibrarian(hireLibrarianID,hireLibrarianPassword)"> Hire librarian</button>
        </td>
      </tr>
    </table>
    <table class="half">
      <tr>
        <td>
          <p style="font-size:30px">
          Fire a librarian
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="fireLibrarianID" placeholder="ID to be Fired">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || currLib!=1 || !fireLibrarianID" @click="fireLibrarian(fireLibrarianID)"> Fire librarian</button>
        </td>
      </tr>
    </table>
    <table class="third">
      <tr>
        <td>
          <p style="font-size:30px">
          Create a shift
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createShiftID" placeholder="ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createDayOfWeek" placeholder="Day of Week">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createStartTime" placeholder="Start Time">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createEndTime" placeholder="End Time">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || currLib!=1 ||!createShiftID || !createDayOfWeek || !createStartTime || !createEndTime" @click="createShift(createShiftID,createDayOfWeek,createStartTime,createEndTime)"> Create shift</button>
        </td>
      </tr>
    </table>
    <table class="third">
      <tr>
        <td>
          <p style="font-size:30px">
          Update a shift
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateShiftID" placeholder="ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateDayOfWeek" placeholder="New Day of Week">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateStartTime" placeholder="New Start Time">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateEndTime" placeholder="New End Time">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || currLib!=1 || !updateShiftID || !updateDayOfWeek || !updateStartTime || !updateEndTime" @click="updateShift(updateShiftID,updateDayOfWeek,updateStartTime,updateEndTime)"> Update shift</button>
        </td>
      </tr>
    </table>
    <table class="third">
      <tr>
        <td>
          <p style="font-size:30px">
          Delete a shift
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="deleteShiftID" placeholder="ID to be deleted">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || currLib!=1 || !deleteShiftID" @click="deleteShift(deleteShiftID)"> Delete shift</button>
        </td>
      </tr>
    </table>
    <table class="half">
      <tr>
        <td>
          <p style="font-size:30px">
          Assign shift to librarian
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="assignShiftID" placeholder="Shift ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="assignLibrarianID" placeholder="Librarian ID">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || currLib!=1  || !assignShiftID || !assignLibrarianID" @click="assignShift(assignShiftID,assignLibrarianID)"> Assign shift</button>
        </td>
      </tr>
    </table>
    <table class="half">
      <tr>
        <td>
          <p style="font-size:30px">
          Unassign shift to librarian
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="unAssignShiftID" placeholder="Shift ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="unAssignLibrarianID" placeholder="Librarian ID">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || currLib!=1  || !unAssignShiftID || !unAssignLibrarianID" @click="unAssignShift(unAssignShiftID,unAssignLibrarianID)"> Unassign shift</button>
        </td>
      </tr>
    </table>
    <table class="third COI">
      <tr>
        <td>
          <p style="font-size:30px">
          Create COI
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createCOIID" placeholder="ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createCOIName" placeholder="Name">
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
          <input type="text" v-model="createCOIBorrowingPeriod" placeholder="Borrowing Period">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createCOIDate" placeholder="Date Borrowed">
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
          <p style="font-size:30px">
          Update COI
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateCOIID" placeholder="ID">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateCOIName" placeholder="New Name">
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
          <input type="text" v-model="updateCOIBorrowingPeriod" placeholder="New Borrowing Period">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateCOIDate" placeholder="New Date">
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
          <p style="font-size:30px">
          Delete COI
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="deleteCOIID" placeholder="ID to be Deleted">
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
          <p style="font-size:30px">
          Create NCOI
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="createNCOIID" placeholder="ID">
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
          <input type="text" v-model="createNCOIName" placeholder="Name">
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
          <p style="font-size:30px">
          Update NCOI
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="updateNCOIID" placeholder="ID">
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
          <input type="text" v-model="updateNCOIName" placeholder="New Title">
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
          <p style="font-size:30px">
          Delete NCOI
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" v-model="deleteNCOIID" placeholder="ID to be Deleted">
        </td>
      </tr>
      <tr>
        <td>
          <button v-bind:disabled="!currLib || currLib!=1 || !deleteNCOIID" @click="deleteNCOI(deleteNCOIID)"> Delete NCOI</button>
        </td>
      </tr>
    </table>
    <div>
    <table class="centerpage">
      View all librarians
      <tr v-for="librarian in librarians" :key="librarian.id">
        <td>
          {{librarian.id}}
        </td>
      </tr>
    </table>
    </div>
    <div>
    <table class="centerpage">
      View all shifts
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
