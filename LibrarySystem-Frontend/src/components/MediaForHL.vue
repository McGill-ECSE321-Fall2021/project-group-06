<template> 
<div id="Media">
  <h2>
    bruh
  </h2>
  <table>
  <tr v-for="checkOutItem in checkOutItems" :key="checkOutItem.id">
    <td>
      {{ checkOutItem.mediaID }}
      {{ checkOutItem.mediaType }}
      {{ checkOutItem.borrowingPeriod }}
      {{ checkOutItem.checkedOut }}
      {{ checkOutItem.reserved }}
      {{ checkOutItem.mediaName }}
    </td>
  </tr>
  <tr v-for="nonCheckOutItem in nonCheckOutItems" :key="nonCheckOutItem.id">
    <td>
      {{ nonCheckOutItem.mediaID }}
      {{ nonCheckOutItem.mediaType }}
      {{ nonCheckOutItem.mediaName }}
    </td>
  </tr>
</table>
<button @click="switchToHeadLibrarian()"> go back to HeadLibrarian menu</button>
<p>
  <span v-if="errorLogin" style="color:red">Erroçr: {{errorLogin}} </span>
</p>
</div>
</template>
<script src="./js/MediaForHeadLibrarian.js">
</script>
<style>
</style>
      