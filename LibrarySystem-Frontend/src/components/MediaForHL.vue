<template> 
<div id="Media">
   <div class="container">
  <h2>
    List of Media
  </h2>
  </div>
<div class="container">
   <table class="center centerpage md htable">
    <colgroup span="9" style="width: 300px"> </colgroup>
    <tr style = "background-color: #fff1e6;">
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
   </table>
  <div id="scrollBox">
  <table class="center centerpage md">
    <colgroup span="9" style="width: 300px"> </colgroup>
    <tr v-for="checkOutItem in checkOutItems" :key="checkOutItem.id">
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
  </div>
<button @click="switchToHeadLibrarian()"> Go Back to Head Librarian Menu</button>
<p>
  <span v-if="errorLogin" style="color:red">Error: {{errorLogin}} </span>
</p>
</div>
</template>
<script src="./js/MediaForHeadLibrarian.js">
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
    /* width: 1800px; */
    height: 700px;
    text-align: center;
    border-spacing: 15px;
    border-collapse: seperate;

    background: rgba(255, 255, 255, 0.06);
    border-radius: 0px 0px 16px 16px;
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
    .htable{
    text-align: center;
    border-spacing: 15px;
    border-collapse: separate;

    background: rgba(255, 255, 255, 0.06);
    border-radius: 16px 16px 0px 0px;
    box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
    backdrop-filter: blur(3.2px);
  }
</style>
      