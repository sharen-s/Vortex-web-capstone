<template>
  <div id="main-forum-feed">
    
    
    <post v-for="post in this.$store.state.posts" v-bind:key="post.postId" v-bind:post="post" />
    
  </div> 

</template>

<script>
import PostsService from "../services/PostsService";
import Post from "../components/Post.vue";
import interactionsService from "../services/InteractionsService"

export default {
  name: "forum-main-feed",
  components: {
    Post
  },
  data() {
    return {
      postsByForum: [],
      errorMessage: false
    };
  },
 
  created() {
    PostsService.getForumPosts(this.$route.params.id).then((response) => {
      this.postsByForum = response.data
      this.$store.commit("SET_POSTS", response.data)
      console.log(this.$store.state.posts)
    });
    interactionsService.getInteractions(this.$store.state.user.id).then((response) =>{
      this.$store.commit("SET_INTERACTIONS", response.data)
    })
  },
};
</script>

<style>
  #main-forum-feed {
    padding: 30px;
    display: flex;
    flex-direction: column;
  }

  
</style>
