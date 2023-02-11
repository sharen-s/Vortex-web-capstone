<template>
  <div class="post-and-replies">
    <button v-show="isModerator" v-on:click="deletePost">Delete Post</button>
    <post class="singular-post" v-for="post in posts" v-bind:key="post.postId" v-bind:post="post" />
    <replies-feed />
  </div>
</template>

<script>
import Post from "../components/Post.vue";
import postsService from "../services/PostsService";
import interactionsService from '../services/InteractionsService'
import RepliesFeed from "../components/RepliesFeed.vue";

export default {
  components: { Post, RepliesFeed },
  name: "post-and-replies",
  data() {
    return {
      posts: [{
        forumId: "",
        userId: null
      }],
      forum: {}
    };
  },
  computed: {
    isModerator(){
      if(this.$store.state.token == ''){
        return false;
      }
      if(this.$store.state.user.authorities[0].name == "ROLE_ADMIN" || this.$store.state.user.id == this.posts[0].userId){
        return true;
      }
      for(let i=0; i <this.$store.state.forumsModerated.length; i++){
        let currentObj = this.$store.state.forumsModerated[i];
        if(currentObj.forumId == this.$store.state.posts[0].forumId){
          return true;
        }
      }
      return false;
    }
  },
  methods: {
    deletePost(){
      
      if(confirm("Are you sure you want to delete this post?")){
        postsService.deletePost(this.$route.params.id).then((response) =>{
          if(response.status === 204){
            this.$router.push({name: 'forum', params:{id:this.posts[0].forumId}})
          }
        })
      }
      
    }
  },
  created() {
    postsService.getPost(this.$route.params.id).then((response) => {
      this.posts = response.data;
      this.$store.commit("SET_POSTS", response.data)
    });
    interactionsService.getInteractions(this.$store.state.user.id).then(response =>{
      this.$store.commit("SET_INTERACTIONS", response.data)
    })
    
  },
};
</script>

<style>
.singular-post{
  margin-top: 10px;
}
.post-and-replies {
  margin-top: 100px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

button {
  margin-top: 1rem;
  padding: 0.5rem 1rem;
  background-color: #23468A;
  color: #fff;
  border: none;
  border-radius: 5px;
  font-weight: bold;
  cursor: pointer;
}

button:hover {
  background-color: #09A0D8;
}
</style>
