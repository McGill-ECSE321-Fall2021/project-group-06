<template> 
<div id="Event" class="center centerpage evt">
  <div class="container">
  <h2>
    View Events
  </h2>
  </div>
<div class="container">
  <div class="columnCEN">
    <table class="center centerpage ohnoh">
      <tr>
        <th> Name </th>
        <th> Date </th>
        <th> Start Time </th>
        <th> End Time </th>
      </tr>
      <tr v-for="event in events" :key="event.id">
        <td> {{ event.name}} </td>
        <td> {{event.eventDate}} </td>
        <td> {{event.eventStart}} </td>
        <td> {{event.endTime}} </td>
      </tr>
    </table>
  </div>
  </div>
  <button id="btn" @click="switchToLogin()"> Go Back to Main Menu</button>
  <p>
    <span v-if="errorShift" style="color:red">Error: {{errorShift}} </span>
  </p>
</div>
</template>

<script src="./js/Event.js">
</script>

<style>
.column {
  float: left;
  vertical-align: 50%;
  width: 80%;
  padding: 10px;
}
.columnCEN {
  float: center;
  /* vertical-align: 50%; */
  /* width: 50%; */
  padding: 10px;
}

.container {
  display: flex;
  justify-content: center;
}
.center{
  text-align: center;
  font-weight: bold;
}
.centerpage{
  margin-left: auto;
  margin-right: auto
}

#Event{
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  color: #2c3e50;
}
#btn{
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  color: #2c3e50;
  margin-top: 30px;
}
html{
  height: 100%;
}
body{
  background: #fff1e6
}
  table{
    min-width: 700px;
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