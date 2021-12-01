<template> 
<div id="Media">
  <h2>
    List of Media
  </h2>
  <table class="center centerpage md">
  <tr v-for="checkOutItem in checkOutItems" :key="checkOutItem.id">
    <td>
      ID : {{ checkOutItem.mediaID }} |
      Type: {{ checkOutItem.mediaType }} |
      Period of Checking: {{ checkOutItem.borrowingPeriod }} |
      Checked Out: {{ checkOutItem.checkedOut }} |
      Reserved: {{ checkOutItem.reserved }} |
      Name: {{ checkOutItem.mediaName }} |
      Start of Borrowing: {{ checkOutItem.startDate }} |
      User Reserving: {{ checkOutItem.userReserving }} |
      User Checking: {{ checkOutItem.userChecking }}
    </td>
  </tr>
  <tr v-for="nonCheckOutItem in nonCheckOutItems" :key="nonCheckOutItem.id">
    <td>
      ID: {{ nonCheckOutItem.mediaID }} |
      Type: {{ nonCheckOutItem.mediaType }} |
      Name: {{ nonCheckOutItem.mediaName }} 
    </td>
  </tr>
</table>
<button @click="switchToLibrarian()"> Go back to librarian menu</button>
<p>
  <span v-if="errorLogin" style="color:red">Error: {{errorLogin}} </span>
</p>
</div>
</template>
<script src="./js/MediaForLibrarian.js">
</script>
<style>
  .center {
    text-align: center;
    font-weight: bold;
  }
  .centerpage {
    margin-left: auto;
    margin-right: auto;
  }
  td{
    padding-top: 5px;
    padding-bottom: 5px;
    padding-right: 10px;
    padding-left: 10px
  }
  #Media {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
  }
  html{
    height: 100%;
  }
  body{
    background: #fff1e6
  }
  table {
    width: 90%;
    min-height: 300px;
    text-align: center;
    border-spacing: 15px;
    border-collapse: seperate;

    background: rgba(255, 255, 255, 0.06);
    border-radius: 16px;
    box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
    backdrop-filter: blur(3.2px);
  }
  .md td {
    border-radius: 16px;
	  padding: 20px;
	  background-color: rgba(192, 27, 27, 0.2);
	  color: #2c3e50;
  }
  .md tr:hover {
    background-color: rgba(109, 88, 88, 0.5)
  }
</style>
      