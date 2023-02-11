
<template>
  <div id="replies-component">
    <h3 id="write-reply" >Write Reply Here</h3>
    <form class="create-reply">
      <div class="body">
        <textarea
          id="reply-body"
          placeholder="Reply to the post!"
          v-model="newReply.body"
          required
        ></textarea>
      </div>
      <div class="actions">
        <button v-on:click="submitReply()">Submit</button>
      </div>
    </form>

    <div
      class="replies-feed"
      v-for="reply in repliesByPost"
      v-bind:key="reply.commentId"
    >
      
      <!-- need to get the Username from the userId. Maybe change the sql statement 
      to join the user table and select the name-->

      <p>{{ reply.body }}</p>
      <h4>from {{ reply.username }} on {{ reply.postedAt }}</h4>
      <!-- <delete-replies v-bind:postId="reply.postId" v-bind:commentId="reply.commentId" v-on:replyDeleted="removeReply(reply)"/> -->
      <div class="delete-button">
      <button v-show="isModerator" v-on:click="deleteReply(reply)">Delete Reply</button>
      </div>
      </div>
  </div>
</template>

<script>
import replyService from "../services/ReplyService";
import moderatorsService from "../services/ModeratorsService"
import postsService from "../services/PostsService"
//import DeleteReplies from './DeleteReply.vue';

export default {
  components: { 
    //DeleteReplies 
    },
  name: "replies-component",
  data() {
    return {
      repliesByPost: [],
      post: [{
        forumId: null
      }
      ],
      newReply: {
        userId: this.$store.state.user.id,
        postId: this.$route.params.id,
        body: "",
      },
    };
  },
  computed: {
    isModerator(){
      if(this.$store.state.token == ''){
        return false;
      }
      if(this.$store.state.user.authorities[0].name == "ROLE_ADMIN"){
        return true;
      }
      for(let i=0; i < this.$store.state.forumsModerated.length; i++){
        let currentObj = this.$store.state.forumsModerated[i];
        if(currentObj.forumId === this.post[0].forumId){
          return true;
        }
      }
      return false;
    }
  },
  methods: {
    submitReply() {
      if(this.$store.state.token == ''){
        this.$router.push({name: 'login'})
      }
      replyService.createReply(this.newReply).then((response) => {
        if (response.status === 201) {
          this.$router.push({
            name: "postAndReplies",
            params: { id: this.newReply.postId },
          });
        }
      });
    },
    deleteReply(reply) {
      replyService.deleteReply(reply.commentId).then((response)=>{
        if(response.status==204){
          this.repliesByPost = this.repliesByPost.filter(r => r.commentId !== reply.commentId);
        }
      })
      
    }
  },
  
  created() {
    replyService.getPostReplies(this.$route.params.id).then((response) => {
      this.repliesByPost = response.data;
      this.repliesByPost.forEach((element)=>{
        element.postedAt = element.postedAt.substring(0,10) + " " + element.postedAt.substring(11,16)
      })
    });
    if(this.$store.state.token != ''){
      moderatorsService.getForumsModerated(this.$store.state.user.id).then((response)=>{
        if(response.status==200){
          this.$store.commit("SET_FORUMS_MODERATED", response.data);
        }
      })
    }
    postsService.getPost(this.$route.params.id).then((response) =>{
      this.post = response.data
    })
  },
};
</script>

<style scoped>
#write-reply{
  padding-bottom: 4px;
}
.new-reply-form {
  display: flex;
  flex-direction: column;
}
  #replies-component {
    background-color: #f7f7f7;
    padding: 1rem;
    border-radius: 2px;
    width: 600px;
  }
  .create-reply {
    background-color: #fff;
    padding: 1rem;
    border-radius: 2px;
    box-shadow: 0 1px 2px rgba(0, 0, 0, 0.06);
    margin-bottom: 1rem;
  }
 .create-reply h3 {
    margin-bottom: 0.5rem;
  }
  .create-reply textarea {
    width: 525px;
    min-height: 4rem;
    padding: 0.5rem;
    border-radius: 2px;
    border: 1px solid #dcdce1;
    font-size: 1rem;
  }
 .create-reply .actions {
    display: flex;
    justify-content: flex-end;
    margin-top: 0.5rem;
  }
  .actions button {
    background-color: #23468A;
    color: #fff;
    border: none;
    font-size: 1rem;
    cursor: pointer;
    border-radius: 19px;
    width: 100px;
    padding: 7px;
    font-weight: bold;
    cursor: pointer;
    color: #f3f3f3;
 }
.actions button:hover {
  background-color: #1483d6;
}
  
  .replies-feed {
    background-color: #fff;
    padding: 1rem;
    border-radius: 2px;
    box-shadow: 0 1px 2px rgba(0, 0, 0, 0.06);
    margin-bottom: 1rem;
  }
  .replies-feed h4 {
    margin-bottom: 0.5rem;
    font-size: 1.0rem;
  }
.replies-feed p {
    font-size: 1rem;
    line-height: 1.5;
  }

button{
  border-radius: 13px;
  width: 110px;
  border: none;
  padding: 7px;
  font-weight: bold;
  cursor: pointer;
  background-color: #23468A;
  color: #f3f3f3;
  margin-top: 50px;
  margin-left: 0vw;
}
button:hover {
  background-color: #1483d6;
}

@media only screen and (max-width: 600px) {
  #replies-component {
    width: 90%;
  }
  .create-reply textarea {
    width: 100%;
  }
}
</style>
