<template>
  <div class="container wrapper">
    <div class="file-upload">
      <input type="file" class="form-control" ref="fileUpload" @change="change">
      <i class="fa fa-arrow-up"></i>
    </div>
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

<style lang="scss" scoped>
.wrapper {
  width:100%;
  height:100%;
  display:flex;
  align-items:center;
  justify-content:center;

  .file-upload {
    height:200px;
    width:200px;
    border-radius: 100px;
    position:relative;
    display:flex;
    justify-content:center;
    align-items: center;
    border:4px solid #FFFFFF;
    overflow:hidden;
    background-image: linear-gradient(to bottom, #2590EB 50%, #FFFFFF 50%);
    background-size: 100% 200%;
    transition: all 1s;
    color: #FFFFFF;
    font-size:100px;

    input[type='file']{
      height:200px;
      width:200px;
      position:absolute;
      top:0;
      left:0;
      opacity:0;
      cursor:pointer;
    }

    &:hover{
      background-position: 0 -100%;
      color:#2590EB;
    }
  }
}
</style>
