<template>
  <div>
    <form>
      <label>Username:</label>
      <input v-model="username" type="text"/>
      <br><br>
      <button @click="promoteModerator">Promote as Moderator</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'PromoteModerator',
  data() {
    return {
      username: ''
    }
  },
  methods: {
    async promoteModerator() {
      try {
        const response = await axios.put('/api/forum/promote', {
          username: this.username
        });
        console.log(response.data);
      } catch (error) {
        console.error(error);
      }
    }
  }
}
</script>
<style scoped>

</style>