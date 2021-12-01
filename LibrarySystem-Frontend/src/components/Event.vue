<template> 
<div id="Event" class="center centerpage evt">
  <h2>
    View Events
  </h2>
  <div id="scrollBox" >
    <table>
      <tr v-for="event in events" :key="event.id">
        <td>
          Name: {{event.name}}
          Date: {{event.date}}
          Start Time: {{event.eventStart}}
          End Time: {{event.endTime}}
        </td>
      </tr>
    </table>
  </div>
  <button id="btn" @click="switchToLogin()"> Go back to main menu</button>
  <p>
    <span v-if="errorShift" style="color:red">Error: {{errorShift}} </span>
  </p>
</div>
</template>
<script src="./js/Event.js">
</script>
<style>
#scrollBox {
  border: 1px solid #2c3e50;
  min-width: 300px;
  min-height: 200px;
  overflow: scroll;
  /*float:left*/
  /*I thought it'd be better to have it center aligned 
  since there's only the scrollbar to view mainly*/
}
.center{
  text-align: center;
  font-weight: bold;
}
.centerpage{
  margin-left: 75px;
  margin-right: 75px;
}
.evt td{
  border-radius: 16px;
	padding: 20px;
  text-align:center;
	background-color: rgba(192, 27, 27, 0.2);
	color: #2c3e50;
}
.evt tr:hover {
  background-color: rgba(109, 88, 88, 0.5)
}
#Event{
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  color: #2c3e50;
}
#btn{
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  color: #2c3e50;
}
html{
  height: 100%;
}
body{
  background: #fff1e6
}
</style>