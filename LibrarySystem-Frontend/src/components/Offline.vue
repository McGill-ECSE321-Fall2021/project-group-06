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
        <tr v-for="media in checkedOutMedias" :key="media.name">
            Name: {{ media.mediaName }} | ID: {{ media.mediaID }}
          </tr>
    </table>
    </div>
      <div class="column">
      <h2>List of Events</h2>
      <table class="center centerpage ohnoh scroll">
             <tr v-for="event in events" :key="event.name">
            Name: {{ event.name }}
          </tr>
    </table>
    </div>
    </div>

    <!-- <div class="container">
      <div class="column">
        <h2>List of Media</h2>
        <div style="overflow-y:auto">
        <table class="User_Media" id = "scrollBox">
          <tr v-for="media in checkedOutMedias" :key="media.name">
            Name: {{ media.mediaName }} | ID: {{ media.mediaID }}
          </tr>
        </table>
        </div>
      </div>
      <div class="column">
        <h2>List of Events</h2>
        <div style="overflow-y:auto">
        <table class="sb2" border=1 frame=hsides rules=rows style="width:100%">
          <tr v-for="event in events" :key="event.name">
            Name: {{ event.name }}
          </tr>
        </table>
        </div>
      </div>
    </div> -->
    <div class="buttonContainer">
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
  table.Account_Table{
    min-height: 300px;
    min-width: 500px;
    max-width: 500px;
    text-align:center;
    /*float: left; */
    border-collapse: collapse;

    background: rgba(255, 255, 255, 0.06);
    border-radius: 16px;
    box-shadow: 0px 4px 30px 5px rgba(0, 0, 0, 0.1);
    backdrop-filter: blur(3.2px);
    float: middle;
    margin-top: 30px;
  }
  table.UpdateAcc{
    min-height: 300px;
    min-width: 450px;
    max-width: 450px;
    text-align:center;
    /*float: left; */
    border-spacing: 1px;
    border-collapse: separate;

    background: rgba(255, 255, 255, 0.06);
    border-radius: 16px;
    box-shadow: 0px 4px 30px 5px rgba(0, 0, 0, 0.1);
    backdrop-filter: blur(3.2px);
    float: right;
    margin-top: 30px;
  }
  table.User_Media{
    min-width: 500px;
    max-width: 500px;
    text-align:center;
    /*float: left; */
    border-spacing: 1px;
    border-collapse: separate;

    background: rgba(255, 255, 255, 0.06);
    border-radius: 16px;
    /*box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);*/
    backdrop-filter: blur(3.2px);
    float: middle;
  }
  table.User_Event{
    min-width: 500px;
    /* max-width: 450px; */
    text-align:center;
    /*float: left; */
    border-spacing: 1px;
    border-collapse: separate;

    background: rgba(255, 255, 255, 0.06);
    border-radius: 16px;
    /*box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);*/
    backdrop-filter: blur(3.2px);
    float: right;
  }
  table.Media{
    min-width: 500px;
    min-height: 300px;
    text-align:center;
    border-spacing: 1px;
    border-collapse: separate;

    background: rgba(255, 255, 255, 0.06);
    border-radius: 16px;
    box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
    backdrop-filter: blur(3.2px);
    float: middle;
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
  width: 33%;
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
  justify-content: space-between;
}
.buttonContainer {
  margin-bottom: 20px;
  margin-top: 20px;
  margin-right: 20px;
  text-align: right;
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