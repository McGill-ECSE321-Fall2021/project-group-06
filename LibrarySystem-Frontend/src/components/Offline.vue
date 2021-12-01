<template>

  <div id="Offline">
    <h1>User Offline Account</h1>
    <div class = "container>">
    <h2>Personal Information</h2>
    <div class="columnCEN">
      <table class="center centerpage ohnoh">
      <tr>
        <!-- <td> Monday </td> <td> 9:00:00 to 20:00:00 -->
          <td> ID Number </td> <td> {{Account.id}}
        </td>
      </tr>
      <tr>
        <td> Name </td> <td> {{Account.name}}
        </td>
      </tr>
      <tr>
        <td>
          Home Address </td> <td> {{Account.address}}
        </td>
      </tr>
      <tr>
        <td>
          Number of Checked Out Media </td> <td>  {{Account.numChecked}}
        </td>
      </tr>
    </table>
    </div>
    </div>

    <div class = "container">
      <div class="column">
      <h2>List of Media</h2>
      <table class="center centerpage ohnoh scroll">
        <tr>
          <th>Name</th>
          <th>MediaID</th>
        </tr>
        <tr v-for="media in medias" :key="media.name">
            <td>{{ media.mediaName }}</td><td>{{ media.mediaID }}</td>
          </tr>
    </table>
    </div>
      <div class="column">
      <h2>List of Events</h2>
      <table class="center centerpage ohnoh scroll">
              <tr>
          <th>Name</th>
        </tr>
             <tr v-for="event in events" :key="event.name">
            <td>{{ event.name }}</td>
          </tr>
    </table>
    </div>
    </div>
    <div>
      <button @click="switchToLogin()"> Sign Out</button>
      <p>
        <span v-if="errorEvent" style="color:red">Error: {{errorEvent}} </span>
      </p>
    </div>
  </div>
</template>
<script src="./js/Offline.js">
</script>
<style scoped>
/* @import '/assets/design.scss'; */
</style>
<style>
/* @import '/assets/design.scss'; */
  #Offline {
    min-height: 800px;
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    /* background: #f2ece8; */
  }  
  
#persInfo {
  margin-top: 50px;
}

#scrollBox {
  border: 1px solid black;
  width: 300px;
  height: 200px;
  overflow: auto;
  float: left;
}
  html{
    height: 100%;
  }
  body{
    background: #fff1e6
  }
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

  td{
    min-width: 250px;
    padding-top: 5px;
    padding-bottom: 5px;
    padding-right: 10px;
    padding-left: 10px
  }
  .center {
    text-align: center;
    font-weight: bold;
    /* border: 7px solid #332d2f; */
  }
  .centerpage {
    margin-left: auto;
    margin-right: auto;
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

  .scroll {
    overflow-y: auto;
    max-height: 500px;
  }

</style>