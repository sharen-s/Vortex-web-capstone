<template>
  <div>
    <div v-if="routeOption == 'searchForForums'" class="suggestions">
      <div v-for="forum in filteredList" v-bind:key="forum.id">
        <router-link
          v-bind:to="{ name: 'forum', params: { id: forum.id } }"
          :style="{ color: link.isActive ? activeColor : inactiveColor }"
          @click="link.isActive = true"
        >
          <div class="forum-listing">
            <h2 id="header">
              {{ forum.forumName }}
            </h2>

            <p id="description">
              {{ forum.description }}
            </p>
          </div>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import ForumsService from "../services/ForumsService";

export default {
  name: "forum-search-result-component",

  data() {
    return {
      allForums: [],
      routeOption: this.$route.params.option,
      searchWord: this.$route.params.query,
      link: {
          isActive: false
      },
      activeColor: '#09A0D8',
      inactiveColor: '#23468A',

    };
  },

  computed: {
    filteredList() {
      return this.allForums.filter((forum) => {
        if (
          forum.forumName.toLowerCase().includes(this.searchWord.toLowerCase())
        ) {
          return forum;
        }
      });
    },
  },
  created() {
    ForumsService.getAllForums().then((response) => {
      this.allForums = response.data;
    });
  },
};
</script>

<style scoped>
.forum-listing {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-areas:
    "header"
    "description";
  border: 1px solid rgb(209, 209, 209);
  padding: 20px;
  margin-bottom: 20px;
  width: 600px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
  background-color: #fcfcfc;
  align-items: center;
  justify-content: center;
}
#header {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

#description {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
</style>