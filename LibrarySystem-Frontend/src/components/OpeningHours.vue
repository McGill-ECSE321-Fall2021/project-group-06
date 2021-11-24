<template>
  <div id="openinghours">
    <h2>Opening Hours</h2>
    <table>
      <tr v-for="openingHour in openingHours" :key="openingHour.name" >
            <td>{{ openingHour.name }}</td>
            <td>
            </td>
        </tr>
    </table>
    <p>
        <span v-if="errorOH" style="color:red">Error: {{errorOH}} </span>
    </p>
  </div>
</template>

<script src="./js/OpeningHours.js">
</script>


<style scoped>
  #openinghours {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    background: #f2ece8;
  }
</style>
