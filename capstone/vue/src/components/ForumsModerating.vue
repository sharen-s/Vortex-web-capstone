<template>
  <div class="forums-moderating">
      <h3>Forums you moderate</h3>
      <p v-if="$store.state.forumsModerated.length==0">None yet! 
          <router-link v-bind:to="{name: 'createForum'}">Create Forum</router-link>
        </p>
      
      <nav>
        <router-link id="moderating" v-bind:to="{name: 'forum', params: {id: forum.forumId}}" v-for="forum in $store.state.forumsModerated" v-bind:key="forum.forumId">
            {{forum.forumName}}
        </router-link>
        </nav>
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>