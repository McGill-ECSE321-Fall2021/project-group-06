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
<p>
  <span v-if="errorLogin" style="color:red">Error: {{errorLogin}} </span>
</p>
</div>
</template>
<script src="./js/Media.js">
</script>
<style>
</style>
      