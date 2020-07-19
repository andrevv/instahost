<template>
  <div class="container">
    <h1>{{id}}</h1>
  </div>
</template>

<script>
export default {
  name: 'Upload',
  props: {
    id: {
      type: String,
      required: true
    }
  }
}
</script>
