<template> 
<div id="Shift" class="centerpage center shft">
  <h2>
    View Shift(s)
  </h2>
<div id="scrollBox">
  <table>
  <colgroup span="3" style="width: 200px"> </colgroup>
  <tr v-for="shift in shifts" :key="shift.id">
    <td>
      <!-- {{ shift.shiftID }} -->
      {{ shift.dayOfWeek }} </td>
    <td> {{ shift.startTime }} </td>
    <td>  {{ shift.endTime }} </td>
  </tr>
</table>
</div>
<p>
  <button @click="switchToLibrarian()"> Go back to librarian menu</button>
</p>
<p>
  <span v-if="errorShift" style="color:red">Error: {{errorShift}} </span>
</p>
</div>
</template>
<script src="./js/ViewShift.js">
</script>
<style>
  #scrollBox {
    width: 1000px;
    height: 300px;
    text-align: center;
    border-spacing: 15px;
    border-collapse: seperate;

    background: rgba(255, 255, 255, 0.06);
    border-radius: 16px;
    box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
    backdrop-filter: blur(3.2px);
    overflow: auto;
    margin-left: auto;
    margin-right: auto
    /*float: left;*/
  }
  html{
    height: 100%;
  }
  body{
    background: #fff1e6
  }
  #Shift{
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
  }
  .centerpage{
    margin-left: 75px;
    margin-right: 75px;
  }
  .center{
    text-align: center;
    font-weight: bold;
  }
  .shft tr{
    border-radius: 16px;
	  padding: 20px;
    text-align:center;
	  background-color: rgba(192, 27, 27, 0.2);
	  color: #2c3e50;
  }
  .shft tr:hover {
    background-color: rgba(109, 88, 88, 0.5)
  }
</style>