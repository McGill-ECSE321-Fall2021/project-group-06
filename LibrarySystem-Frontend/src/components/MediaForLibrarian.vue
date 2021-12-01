<template> 
<div id="Media">
  <h2>
    List of Media
  </h2>
  <div id="scrollBox">
  <table class="center centerpage md">
  <colgroup span="9" style="width: 125px"> </colgroup>
  <tr>
    <th> ID </th>
    <th> Type </th>
    <th> Title </th>
    <th> Borrowing Period </th>
    <th> Checkout Status </th>
    <th> Reserve Status </th>
    <th> Checked Out Start Date </th>
    <th> Account that Reserved </th>
    <th> Account that Checked Out </th>
  </tr>
  <tr v-for="checkOutItem in checkOutItems" :key="checkOutItem.id">
    <td>
      <td> {{ checkOutItem.mediaID }} </td>
      <td>  {{ checkOutItem.mediaType }} </td>
      <td>  {{ checkOutItem.mediaName }} </td>
      <td>  {{ checkOutItem.borrowingPeriod }} </td>
      <td>  {{ checkOutItem.checkedOut }} </td>
      <td>  {{ checkOutItem.reserved }} </td>
      <td> {{ checkOutItem.startDate }} </td>
      <td> {{ checkOutItem.userReserving }} </td>
      <td> {{ checkOutItem.userChecking }} </td>
  </tr>
  <tr v-for="nonCheckOutItem in nonCheckOutItems" :key="nonCheckOutItem.id">
    <td> {{ nonCheckOutItem.mediaID }} </td>
      <td> {{ nonCheckOutItem.mediaType }} </td>
      <td>  {{ nonCheckOutItem.mediaName }} </td>
      <td>  -  </td>
      <td>  -  </td>
      <td>  -  </td>
      <td>  -  </td>
      <td>  -  </td>
      <td>  -  </td>
  </tr>
</table>
</div>
<button @click="switchToLibrarian()"> Go Back to Librarian Menu</button>
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
  #Media {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
  }
  #scrollBox {
    width: 1125px;
    height: 500px;
    text-align: center;
    border-spacing: 15px;
    border-collapse: seperate;

    background: rgba(255, 255, 255, 0.06);
    border-radius: 16px;
    box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
    backdrop-filter: blur(3.2px);
    overflow:auto;
    margin-left: auto;
    margin-right: auto
  }
  .md tr {
    border-radius: 16px;
	  padding: 20px;
	  background-color: rgba(192, 27, 27, 0.2);
	  color: #2c3e50;
  }
  .md tr:hover {
    background-color: rgba(109, 88, 88, 0.5)
  }
  html{
    height: 100%;
  }
  body{
    background: #fff1e6
  }
</style>
      