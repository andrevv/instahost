<template>
  <div class="container">
    <h1>Upload File</h1>
    <form>
      <div class="form-group">
        <input type="file" class="form-control" ref="fileUpload" @change="change">
      </div>
    </form>
  </div>
</template>

<script>
export default {
  name: 'App',
  methods: {
    change (e) {
      const formData = new FormData()
      formData.append('file', e.target.files[0])

      fetch(process.env.VUE_APP_API_BASE_URL + '/api/files', {
        method: 'POST',
        body: formData
      })
        .then(res => res.json())
        .then(data => {
          this.$refs.fileUpload.value = null
          this.$router.push({ name: 'Hosted', params: { id: data.id } })
        })
    }
  }
}
</script>
