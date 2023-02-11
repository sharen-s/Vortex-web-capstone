








<template>
  <div class="forum-details">
    <aside class = "left-aside">
    <h2>{{ forum.forumName }} <favorite-checkbox v-bind:forum="forum" /></h2>
    
    <h3>Moderated by:</h3>
    <ul>
      <li
        v-for="moderator in $store.state.moderatorsForForum"
        v-bind:key="moderator.moderatorId"
      >
        {{ moderator.username }}
      </li>
    </ul>
    <span v-show="isModerator">
      <p>Add a moderator?</p>
      <add-moderator />
    </span>
    <span v-if="isAdmin">
      <p>Remove a moderator?</p>
      <label for="moderator-select">Select a user: </label>
      <select id="moderator-select" v-model="selectedUserId">
        <option v-for="moderator in $store.state.moderatorsForForum" v-bind:key="moderator.moderatorId" v-bind:value="moderator.moderatorId">
          {{moderator.username}}
        </option>
      </select>
      <div class="remove-mod-button">
      <button v-on:click="removeModerator" >Remove Moderator</button>
      <p id = "message" v-show="displayMessage"> {{message}}</p>
      </div>
    </span>
    <h3>Description:</h3>
    <p>{{ forum.description }}</p>
    <h3>Rules:</h3>
    <p>{{ forum.rules }}</p>
    
      
  </aside>
  <div class="feed">
    <div class="sorting">
      <router-link v-bind:to="{ name: 'newPost', params: { id: forum.id } }"
      ><button class="create-post-btn">Create Post</button></router-link>
      <label for="order-posts">&nbsp;&nbsp;Sort by: </label>
      <select id="order-posts" v-on:change="handleChange">
        <option value="date" selected>Date</option>
        <option value="popularity">Popularity</option>
      </select>
    </div>
    <forum-main-feed class="posts" id="feed-posts" />
  </div>
      
  </div>
</template>

<script>
import forumsService from "../services/ForumsService";
import ForumMainFeed from "../components/ForumMainFeed.vue";
import FavoriteCheckbox from "../components/FavoriteCheckbox.vue";
import moderatorsService from "../services/ModeratorsService";
import AddModerator from "../components/AddModerator.vue";
import PostsService from "../services/PostsService"

export default {
  components: {
    ForumMainFeed,
    FavoriteCheckbox,
    AddModerator,
  },
  data() {
    return {
      forum: [],
      moderators: [],
      isSortedByDate: true,
      isAdmin: this.$store.state.token==''? false:this.$store.state.user.authorities[0].name=='ROLE_ADMIN',
      selectedUserId: null,
      displayMessage: false,
      message: ''
    };
  },
  methods: {
    handleChange(){
      if(this.isSortedByDate){
        this.$store.commit("SORT_POSTS")
        this.isSortedByDate = false;
      }
      else{
        PostsService.getForumPosts(this.$route.params.id).then((response)=>{
          if(response.status==200){
            this.$store.commit("SET_POSTS", response.data)
            this.isSortedByDate = true;
          }
        })
      }
    },
    removeModerator(){
      if(this.selectedUserId==null){
        this.message = "Please select a user"
        this.displayMessage = true;
      }
      if(this.$store.state.moderatorsForForum.length==1){
        this.message = "Forums must have at least one moderator"
        this.displayMessage = true;
      }
      else{
        let moderatorToDelete = {
          moderatorId : this.selectedUserId,
          forumId: this.$route.params.id
        }
        moderatorsService.removeModerator(moderatorToDelete).then((response)=>{
          if(response.status==204){
            this.$store.commit("REMOVE_MODERATOR", this.selectedUserId);
            this.selectedUserId=null;
            this.message='Moderator removed.'
            this.displayMessage = true;
          }
        })

      }
    }
  },
  computed: {
    isModerator(){
      if(this.$store.state.token == ''){
        return false;
      }
      if(this.isAdmin){
        return true;
      }
      for (let i = 0; i < this.$store.state.moderatorsForForum.length; i++) {
        let currentObj = this.$store.state.moderatorsForForum[i];
        if (currentObj.moderatorId === this.$store.state.user.id) {
          return true;
        }
      }
      return false;
    },
  },
  created() {
    forumsService.getForum(this.$route.params.id).then((response) => {
      this.forum = response.data;
    });

    moderatorsService
      .getModeratorsForForum(this.$route.params.id)
      .then((response) => {
        this.$store.commit("SET_MODERATORS_FOR_FORUM", response.data);
      });

    moderatorsService;
  },
};
</script>

<style>
.forum-details{
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-areas: "left-aside posts posts";
  grid-gap: 20px; 
  margin-top: 80px;  
  background-color: #fcfcfc;  
}

@media only screen and (max-width: 700px) {
  .forum-details {
    grid-template-columns: 1fr;
    grid-template-areas: "left-aside" "posts";
  }
}

.feed{
grid-area: posts;
display: flex;
flex-direction: column;
align-items: center;
}

#feed-posts{
  margin-right: 25vh;
}

@media only screen and (max-width: 700px) {
  #feed-posts {
    margin-right: 0;
  }
}

.sorting{
  margin-right: 25vh;
  padding-top: 10px;
  padding-bottom: 10px;
}

@media only screen and (max-width: 700px) {
  .sorting {
    margin-right: 0;
  }
}

.remove-mod-button{
  margin-bottom: 8px;
}


.create-post-btn:hover {
  background-color: #1483d6;
}
.create-post-btn{
 border-radius: 13px;
  width: 130px;
  border: none;
  padding: 7px;
  font-weight: bold;
  cursor: pointer; 
  background-color: #23468A;
  color: #f3f3f3;
}
h3{
  margin-top: 10px;
}
</style>