<template>
  <div id="headlibrarian">
    <h2>HeadLibrarian</h2>
    <table>
      <tr>
          <td>Test HL</td>
      </tr>
    </table>
    <p>
        <span v-if="errorOH" style="color:red">Error: {{errorHL}} </span>
    </p>
  </div>
</template>

<script src="./js/HeadLibrarian.js">
</script>


<style scoped>
  #headlibrarian {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    background: #f2ece8;
  }
</style>
