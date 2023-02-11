<template>
  <div>
    <!-- <div v-if="routeOption == 'searchForForums'" class="suggestions">
      <div v-for="forum in filteredList" v-bind:key="forum.id">
        <router-link v-bind:to="{ name: 'forum', params: { id: forum.id } }">
          {{ forum.forumName }}
        </router-link>
      </div>
    </div> -->
    <forum-results />
    <div v-if="routeOption == 'searchForPosts'">
      <post
        v-for="post in this.$store.state.posts"
        v-bind:key="post.postId"
        v-bind:post="post"
      />
    </div>
    <div v-if="postsWithKeyword == null && allForums == null">
        Sorry. No results for this search. :(
    </div>
  </div>
</template>

<script>
// import ForumsService from "../services/ForumsService";
import PostsService from "../services/PostsService";
import Post from "../components/Post.vue";
import interactionsService from "../services/InteractionsService";
import ForumResults from '../components/SearchResultForumList.vue'

export default {
  name: "search-results",
  components: {
    Post,
    ForumResults,
    
  },
  data() {
    return {
      searchWord: this.$route.params.query,
      postsWithKeyword: [],
      // allForums: [],
      errorMessage: false,
      routeOption: this.$route.params.option
    };
  },
  computed: {
    // filteredList() {
    //   return this.allForums.filter((forum) => {
    //     if (
    //       forum.forumName.toLowerCase().includes(this.searchWord.toLowerCase())
    //     ) {
    //       return forum;
    //     }
    //   });
    // },
  },
  created() {
    PostsService.searchTitleandBodyByKeyword(this.searchWord).then(
      (response) => {
        this.postsWithKeyword = response.data;
        this.$store.commit("SET_POSTS", response.data);
        console.log(this.$store.state.posts);
      }
    );
    interactionsService
      .getInteractions(this.$store.state.user.id)
      .then((response) => {
        this.$store.commit("SET_INTERACTIONS", response.data);
      });

    // ForumsService.getAllForums().then((response) => {
    //   this.allForums = response.data;
    // });
  },
};
</script>

<style>
</style>