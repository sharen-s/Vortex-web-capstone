<template>
  <div class="results">
   <h2 class="searchResultsHeader">
     In the Vortex:
   </h2>  

    <search-results id="theResults"/>
  </div>
</template>

<script>
import SearchResults from "../components/SearchResults.vue";

export default {
  components: {
    SearchResults,
  },
};
</script>

<style>
#theResults {
  display: flex;
  justify-content: center;
  align-items: center;
}

.searchResultsHeader {
text-align: center;
}

.results{
  margin-top: 100px;
}
</style>