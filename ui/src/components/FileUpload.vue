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
  name: 'FileUpload',
  methods: {
    change (e) {
      const formData = new FormData()
      formData.append('file', e.target.files[0])

      fetch('http://localhost:8081/api/files', {
        method: 'POST',
        mode: 'no-cors',
        body: formData
      })
        .then(() => {
          this.$refs.fileUpload.value = null
        })
    }
  }
}
</script>
