<template>

  <div id="Offline">
    <h2>
      <Offline :massage="massageForChild"> </Offline>
    </h2>
      <table>
      </table>
      <p>
      </p>
  </div>
  
</template>

<script>
export default {
    name: "Offline",
    props: { massageForChild: String }
};
</script>
<style>
  #login {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    background: #f2ece8;
  }
</style>