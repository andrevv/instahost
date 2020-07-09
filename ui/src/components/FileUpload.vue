<template>
  <div class="container">
    <h1>Upload File</h1>
    <form>
      <div class="form-group">
        <input type="text" class="form-control">
      </div>
      <button class="btn btn-primary" @click.prevent="submit">Submit</button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'FileUpload',
  methods: {
    submit () {
      fetch('http://localhost:8081/api/files', {
        method: 'POST',
        mode: 'no-cors'
      })
        .then(respone => console.log(respone))
    }
  }
}
</script>
