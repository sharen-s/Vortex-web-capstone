<template>
  <div class = "top5">
      <h3>Top Forums Today</h3>
        <nav>
        <router-link id="forum-link" v-bind:to="{name: 'forum', params: {id: forum.id}}" v-for="forum in mostRecentForums" v-bind:key="forum.id">
            {{forum.forumName}}
        </router-link>
        </nav>
        
      
  </div>
</template>

<script>
import ForumsService from '../services/ForumsService';

export default {
    data() {
        return { mostRecentForums : []
        }
    
},

created() {
    ForumsService.displayTop5().then(
        (response) => {
            this.mostRecentForums = response.data;
        }
    );
    }
};
</script>

<style scoped>


nav a {  
  display: flex;
  align-items: center;
  width: 100%;
  text-align: left;
  text-decoration: none;
  color: black;
  
} 

h3 {
  margin: 0;
  font-size: 20px;
  color: #23468A;
}  

#forum-link:hover{
  color:#09A0D8;
}

</style> 