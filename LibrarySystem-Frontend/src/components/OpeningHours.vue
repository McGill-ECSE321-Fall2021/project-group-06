<template>
  <div id="openinghours">
    <h2>Opening Hours</h2>
    <table class="center centerpage ohnoh">
      <tr>
        <!-- <td> Monday </td> <td> 9:00:00 to 20:00:00 -->
          <td> Monday </td> <td> {{ Monday.startTime }} to {{ Monday.endTime }}
        </td>
      </tr>
      <tr>
        <td> Tuesday </td> <td> {{ Tuesday.startTime }} to {{ Tuesday.endTime }}
        </td>
      </tr>
      <tr>
        <td>
          Wednesday </td> <td> {{ Wednesday.startTime }} to {{ Wednesday.endTime }}
        </td>
      </tr>
      <tr>
        <td>
          Thursday </td> <td> {{ Thursday.startTime }} to {{ Thursday.endTime }}
        </td>
      </tr>
      <tr>
        <td>
          Friday </td> <td> {{ Friday.startTime }} to {{ Friday.endTime }}
        </td>
      </tr>
      <tr>
        <td>
          Saturday </td> <td> Closed
        </td>
      </tr>
      <tr>
        <td>
          Sunday </td> <td> Closed
        </td>
      </tr>
    </table>
    <button @click="switchToLogin()"> Go back to main menu</button>
    <p>
        <span v-if="errorOH" style="color:red">Error: {{errorOH}} </span>
    </p>
  </div>
</template>

<script src="./js/OpeningHours.js">
</script>


<style>
  .center {
    text-align: center;
    font-weight: bold;
    /* border: 7px solid #332d2f; */
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
  #openinghours {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    /* background: #f2ece8; */
  }
  html{
    height: 100%;
  }
  body{
    background: #fff1e6
  }
  /* table{
    	width: 500px;

  } */
    table{
    width: 500px;
    min-height: 300px;
    /* width:33%; */
    text-align:center;
    /* float: left; */
    border-spacing: 15px;
    border-collapse: separate;

    background: rgba(255, 255, 255, 0.06);
    border-radius: 16px;
    box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
    backdrop-filter: blur(3.2px);
  }
  .ohnoh td {
  border-radius: 16px;
	padding: 20px;
	background-color: rgba(192, 27, 27, 0.2);
	color: #2c3e50;
  }
  .ohnoh tr:hover {background-color: rgba(109, 88, 88, 0.5)}
</style>
