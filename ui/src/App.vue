<template>
  <div id="app">
    <FileUpload />
  </div>
</template>

<script>
import FileUpload from '@/components/FileUpload'

export default {
  name: 'App',
  components: {
    FileUpload
  }
}
</script>

<style lang="scss">
@import 'node_modules/bootstrap/scss/bootstrap';
</style>
