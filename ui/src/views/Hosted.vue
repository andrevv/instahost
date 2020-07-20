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
    fetch(process.env.VUE_APP_API_BASE_URL + '/api/files/' + this.id, {
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
