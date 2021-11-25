<template>
  <div id="openinghours">
    <h2>Opening Hours</h2>
    <table>
      <tr>
        <td>
          Monday: {{ Monday.startTime }} to {{ Monday.endTime }} ID: {{ Monday.id }}
        </td>
      </tr>
      <tr>
        <td>
          Tuesday: {{ Tuesday.startTime }} to {{ Tuesday.endTime }} ID: {{ Tuesday.id }}
        </td>
      </tr>
      <tr>
        <td>
          Wednesday: {{ Wednesday.startTime }} to {{ Wednesday.endTime }} ID: {{ Wednesday.id }}
        </td>
      </tr>
      <tr>
        <td>
          Thursday: {{ Thursday.startTime }} to {{ Thursday.endTime }} ID: {{ Thursday.id }}
        </td>
      </tr>
      <tr>
        <td>
          Friday: {{ Friday.startTime }} to {{ Friday.endTime }} ID: {{ Friday.id }}
        </td>
      </tr>
      <tr>
        <td>
          Saturday : Closed
        </td>
      </tr>
      <tr>
        <td>
          Sunday : Closed
        </td>
      </tr>
    </table>
    <p>
        <span v-if="errorOH" style="color:red">Error: {{errorOH}} </span>
    </p>
  </div>
</template>

<script src="./js/HeadLibrarian.js">
</script>


<style scoped>
  #openinghours {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    background: #f2ece8;
  }
</style>
