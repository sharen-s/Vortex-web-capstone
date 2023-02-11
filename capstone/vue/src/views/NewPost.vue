<template>
  <div class="new-post">
      <create-post />
  </div>
</template>

<script>
import CreatePost from '../components/CreatePost.vue'

export default {
    components: {
        CreatePost
    }

}
</script>

<style>
.new-post{
    margin-top: 100px;
}

</style>