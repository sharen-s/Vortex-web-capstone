<template>
  <div class="favorite-forums" >
    <h3>Favorite Forums</h3>
    <p v-show="favoriteForums.length==0">
      None yet! Add a favorite in the forum main page.
    </p>
    <nav>
        <router-link id="forum-link" v-for="forum in favoriteForums" v-bind:key="forum.id" v-bind:to="{ name: 'forum', params: { id: forum.id } }">{{
          forum.forumName
        }}</router-link>
    </nav>
  </div>
</template>

<script>
import forumsService from "../services/ForumsService";

export default {
  name: "favorite-forums-list",
  components: {},
  data() {
    return {
      favoriteForums: [],
    };
  },
  methods: {},
  created() {
    if (this.$store.state.token != "") {
      forumsService
        .favoriteForumsByUserId(this.$store.state.user.id)
        .then((response) => {
          this.favoriteForums = response.data;
        });
    }
  },
};
</script>

<style >

nav a {  
  display: flex;
  align-items: center;
  padding: 10px;
  width: 100%;
  text-align: left;
  text-decoration: none;
  color: black;
}

nav a:hover {
  background-color: #1489b417;
  width: 90%;
  

}

#forum-link:hover{
  color: #09A0D8;


}

h3 {
  margin: 0;
  font-size: 20px;
  color: #23468A;
}

</style>
