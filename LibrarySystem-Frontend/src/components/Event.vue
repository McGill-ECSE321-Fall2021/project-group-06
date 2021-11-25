<template> 
<div>
    <h2>
        View Events
    </h2>
<div id="scrollBox">
  
  <table>
  <tr v-for="event in events" :key="event.id">
    <td>
      {{ event.name}}
      {{event.date}}
      {{event.eventStart}}
      {{event.eventEnd}}
    </td>
  </tr>
  </table>
</div>
  <table>
      <tr>
        <td>
          <p style="font-size:30px">
             <button @click="switchToLogin()"> go back to main menu</button>
          </p>
        </td>
      </tr>
    </table>
<p>
  <span v-if="errorShift" style="color:red">Error: {{errorShift}} </span>
</p>
</div>
</template>
<script>
</script>
<style scoped>
#scrollBox {
  border: 1px solid black;
  width: 300px;
  height: 200px;
  overflow: scroll;
  float: left;
}
  html{
    height: 100%;
  }
  body{
    background: #fff1e6
  }
</style>