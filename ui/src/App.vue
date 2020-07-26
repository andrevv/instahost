<template>
  <div id="app">
    <router-view :key="$route.path" />
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style lang="scss">
@import './../node_modules/bootstrap/scss/bootstrap';
@import './../node_modules/@fortawesome/fontawesome-free/css/all.min.css';
</style>
