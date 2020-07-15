<template>
  <div class="container">
    <h1>Upload File</h1>
    <form>
      <div class="form-group">
        <input type="file" class="form-control" ref="fileUpload" @change="change">
      </div>
      <h1>{{id}}</h1>
    </form>
  </div>
</template>

<script>
export default {
  name: 'FileUpload',
  data () {
    return {
      id: null
    }
  },
  methods: {
    change (e) {
      const formData = new FormData()
      formData.append('file', e.target.files[0])

      fetch('http://localhost:8081/api/files', {
        method: 'POST',
        body: formData
      })
        .then(res => res.json())
        .then(data => {
          this.$refs.fileUpload.value = null
          this.id = data.id
        })
    }
  }
}
</script>
