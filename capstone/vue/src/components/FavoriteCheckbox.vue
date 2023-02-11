<template>
  <div class="favorite-checkbox">
    <label class="icon-checkbox">
    <input

      type="checkbox"
      v-model="favoriteForum"
      v-on:change="onFavoritedChange($event)"
      v-bind:checked="favoriteForum"
    >
    <i v-show="!favoriteForum" class="fa-regular fa-heart"></i>
    <i v-show="favoriteForum" class = "fa-solid fa-heart"></i>
    </label>
  </div>
</template>

<script>
import favoriteService from "../services/FavoriteService";
export default {
  name: "favorite-checkbox",
  props: ["forum"],
  data() {
    return {
      favoriteForum: false,
      favorite: {
        userId: this.$store.state.user.id,
        favoriteId: this.$route.params.id,
      },
    };
  },
  computed: {},
  methods: {
    //need try catches
    // need response status for the deleted post

    onFavoritedChange(event) {
      if (this.$store.state.token == "") {
        this.$router.push("/login");
      } else {
        if (event.target.checked) {
          this.$store.commit("ADD_FAVORITE", this.favorite);
          favoriteService.addFavorite(this.favorite);
          this.favoriteForum = true;
        } else {
          this.$store.commit("DELETE_FAVORITE", this.favorite);
          favoriteService.deleteFavorite(this.favorite);
          this.favoriteForum = false;
        }
      }
    },
    checkFavoriteForum() {
      //do I need to account for if there isn't a favorite object?
      for (let i = 0; i < this.$store.state.favorites.length; i++) {
        let currentObj = this.$store.state.favorites[i];
        if (
          currentObj.userId == this.$store.state.user.id &&
          currentObj.favoriteId == this.$route.params.id
        ) {
          this.favoriteForum = true;
        }
      }
    },
  },
  created() {
    favoriteService.getFavorites(this.$store.state.user.id).then((response) => {
      this.favorites = response.data;
      this.$store.commit("SET_FAVORITES", response.data);
      this.checkFavoriteForum();
    });
  },
};
</script> 

<style scoped>
.favorite-checkbox{
  display: inline-block;
}
input[type="checkbox"] {
  display: none;
}

.icon-checkbox {
  position: relative;
  display: inline-block;
  cursor: pointer;
}

.icon-checkbox i {
  
  font-size: 12pt;
}


.fa-solid.fa-heart {
  color: #09A0D8;
}

</style>