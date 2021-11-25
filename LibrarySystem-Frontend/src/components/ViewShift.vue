<template> 
<div id="Shift">
  <h2>
    View Shift
  </h2>
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
<p>
  <span v-if="errorShift" style="color:red">Error: {{errorShift}} </span>
</p>
</div>
</template>
<script src="./js/ViewShift.js">
</script>
<style scoped>
</style>