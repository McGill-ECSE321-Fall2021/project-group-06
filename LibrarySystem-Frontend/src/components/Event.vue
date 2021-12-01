<template> 
<div id="Event" class="center centerpage evt">
  <h2>
    View Events
  </h2>
  <div id="scrollBox" >
    <table>
      <colgroup span="4" style="width: 250px"> </colgroup>
      <tr>
        <th> Name </th>
        <th> Date </th>
        <th> Start Time </th>
        <th> End Time </th>
      </tr>
      <tr v-for="event in events" :key="event.id">
        <td> {{ event.name}} </td>
        <td> {{event.date}} </td>
        <td> {{event.eventStart}} </td>
        <td> {{event.eventEnd}} </td>
      </tr>
    </table>
  </div>
  <button id="btn" @click="switchToLogin()"> Go Back To Main Menu</button>
  <p>
    <span v-if="errorShift" style="color:red">Error: {{errorShift}} </span>
  </p>
</div>
</template>
<script src="./js/Event.js">
</script>
<style>
#scrollBox {
  width: 1000px;
  height: 300px;
  border-spacing: 15px;
  border-collapse: seperate;

  background: rgba(255, 255, 255, 0.06);
  border-radius: 16px;
  box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
  backdrop-filter: blur(3.2px);
  overflow: auto;
  margin-left: auto;
  margin-right: auto;
  /*float:left*/
  /*I thought it'd be better to have it center aligned 
  since there's only the scrollbar to view mainly*/
}
.center{
  text-align: center;
  font-weight: bold;
}
.centerpage{
  margin-left: auto;
  margin-right: auto
}
.evt tr{
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