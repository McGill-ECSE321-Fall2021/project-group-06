<template>

  <div id="Offline">
    <h2>
        <Login :massage="massageForChild">
            poop: {{massage}}
        </Login>
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
    props: ["massage"]
};
</script>
<style>
  #login {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    background: #f2ece8;
  }
</style>