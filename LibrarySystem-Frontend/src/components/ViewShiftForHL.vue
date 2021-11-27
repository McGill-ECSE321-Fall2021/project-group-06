<template> 
<div id="HLShift" class="centerpage center shft">
  <h2>
    View Shift(s)
  </h2>
  <div id="scrollBox">
  <table>
  <tr v-for="shift in shifts" :key="shift.id">
    <td>
      {{ shift.shiftID }}
      {{ shift.dayOfWeek }}
      {{ shift.startTime }}
      {{ endTime }}
    </td>
  </tr>
</table>
</div>
<p>
  <button @click="switchToHeadLibrarian()"> Go back to head librarian menu</button>
</p>
<p>
  <span v-if="errorShift" style="color:red">Error: {{errorShift}} </span>
</p>
</div>
</template>
<script src="./js/ViewShiftForHL.js">
</script>
<style>
#scrollBox {
  border: 1px solid black;
  min-width: 300px;
  min-height: 200px;
  overflow: scroll;
  /*float: left;*/
}
  html{
    height: 100%;
  }
  body{
    background: #fff1e6
  }
  #HLShift{
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
  .shft td{
    border-radius: 16px;
	  padding: 20px;
    text-align:center
  }
</style>