<template>
    <div class="container">          
      <i class="fa-solid fa-magnifying-glass"></i>
      <form @submit.prevent="search">        
        <div class="input-and-select">
          <input type="text" v-model="query" placeholder="Search Vortex..." />
          <select v-model="selectedSearchOption" class="dropdown-input">
            <option v-for="(options, key) in searchOptions" :key="key" :value="options">
              {{ options.text }}
            </option>
          </select>        
          <button href="search" type="submit" class="submit-search">Go</button>
        </div>
      </form>
    </div>
</template>

<script>
export default {
  name: "search-bar",
  data() {
    return {
      query: "",
      selectedSearchOption: { text: "Posts", value: "searchForPosts" },
      searchOptions: [
        { text: "Forums", value: "searchForForums" },
        { text: "Posts", value: "searchForPosts" },
      ],
    };
  },

  methods: {
    search() {
      if (this.$route.name === "search") {
        this.$router.replace({
          params: {
            query: this.query,
            option: this.selectedSearchOption.value,
          }
        });
        location.reload();
      } else {
        this.$router.push({
          name: "search",
          params: {
            query: this.query,
            option: this.selectedSearchOption.value,
          },
        });
      }
    },
  },
};
</script>

<style scoped>
.container {
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 40px;
  border-radius: 50px;
  background-color: #f5f5f5;
  padding: 0 20px;
}

.input-and-select {
  display: flex;
  align-items: center;
  height: 100%;
  flex: 1;
  padding-left: 20px;
  width: 70%;
}

i {
  margin-right: 10px;
  font-size: 18px;
  color: #23468A;
}

input[type="text"] {
  flex: 1;
  padding: 8px;
  font-size: 16px;
  border: none;
  outline: none;
  background-color: transparent;
  color: #23468A;
}

.dropdown-input {
  padding: 5px 10px;
  border: none;
  outline: none;
  background-color: transparent;
  color: #23468A;
  font-size: 16px;
}

.submit-search {
  padding: 4px 15px;
  font-size: 15px;
  font-weight: bold;
  color: #fff;
  background-color: #23468A;
  border: none;
  border-radius: 50px;
  cursor: pointer;
  margin-left: 10px;
  margin-bottom: 15px;
}

@media screen and (max-width: 767px) {
 .container {
  display: none;
}

}

</style>
