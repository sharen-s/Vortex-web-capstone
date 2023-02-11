<template>
  <div class = "account">
      <h1>Hi, {{$store.state.user.username}}!</h1>
      <forums-moderating />
      <favorite-forums />
  </div>
</template>

<script>
import ForumsModerating from '../components/ForumsModerating.vue'
import moderatorsService from '../services/ModeratorsService'
import FavoriteForums from '../components/FavoriteForums.vue'
export default {
    components: {
        ForumsModerating,
        FavoriteForums
    },
    created(){
        moderatorsService.getForumsModerated(this.$store.state.user.id).then((response)=>{
            if(response.status==200){
                this.$store.commit("SET_FORUMS_MODERATED", response.data)
            }
        })
    }

}
</script>

<style>
.account{
    padding-top: 80px;
    width: 30vh;
    
}

</style>