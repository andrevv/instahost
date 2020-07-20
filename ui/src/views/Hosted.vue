<template>
  <div v-html="html"></div>
</template>

<script>
export default {
  name: 'Hosted',
  data () {
    return {
      html: null
    }
  },
  props: {
    id: {
      type: String,
      required: true
    }
  },
  created () {
    fetch('http://localhost:8081/api/files/' + this.id, {
      headers: {
        Accept: 'text/html'
      }
    })
      .then(res => res.text())
      .then(data => {
        this.html = data
      })
  }
}
</script>
