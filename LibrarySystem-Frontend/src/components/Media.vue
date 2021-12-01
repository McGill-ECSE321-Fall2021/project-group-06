<template> 
<div id="Media">
  <h2>
    List of Media
  </h2>
  <div style="height: 800px; overflow:auto">
  <table class="center centerpage md">
    <tr>
          <th>ID</th>
          <th>Type</th>
          <th>Period Of Borrowing</th>
          <th>Checked Out</th>
          <th>Reserved</th>
          <th>Name</th>
        </tr>
    <tr v-for="checkOutItem in checkOutItems" :key="checkOutItem.id">
        <td>{{ checkOutItem.mediaID }}</td>
        <td>{{ checkOutItem.mediaType }}</td>
        <td>{{ checkOutItem.borrowingPeriod }}</td>
        <td>{{ checkOutItem.checkedOut }}</td>
        <td>{{ checkOutItem.reserved }}</td>
        <td>{{ checkOutItem.mediaName }}</td>
    </tr>
    <tr v-for="nonCheckOutItem in nonCheckOutItems" :key="nonCheckOutItem.id">
        <td>{{ nonCheckOutItem.mediaID }}</td>
        <td>{{ nonCheckOutItem.mediaType }}</td>
        <td>{{ nonCheckOutItem.mediaName }}</td>
    </tr>
  </table>
  </div>
  <button @click="switchToLogin()"> Go back to main menu</button>
  <p>
    <span v-if="errorLogin" style="color:red">Error: {{errorLogin}} </span>
</p>
</div>
</template>
<script src="./js/Media.js">
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
      