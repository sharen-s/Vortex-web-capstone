<template>
   <form v-on:submit.prevent="submitForum" class="cardForm"> 
    <p>Create Forum</p>
<div>
    <label for="name">Forum Name</label>
    <input
    id="name"
    type="text"
    class="form-control"
    v-model="forum.forumName"
    autocomplete="off"
    />
</div>    

<div>
    <label for="description"> Forum Description</label>
    <textarea
        id="description"
        class="form-control"
        v-model="forum.description"
    />
</div>    

<div>
    <label for="rules"> Describe Your Rules Here</label>
    <textarea
        id="rules"
        class="form-control"
        v-model="forum.rules"
    /> 
</div>       

<div>
    <input type="submit"/>
    <button v-on:click.prevent="cancelForm"
      type="cancel"> Cancel 
    </button>  
</div>    

   </form>
</template>

<script>
import forumsService from '../services/ForumsService'

export default {
    // name: "create-forum",
    data() {
        return {
            forum: {
                forumName: '',
                description: '',
                rules: '',
                moderatorList: []
            }
        }
    },

    methods: {
        submitForum() {

          const newForum = {
              forumName: this.forum.forumName,
              description: this.forum.description,
              rules: this.forum.rules,
              moderatorList: [this.$store.state.user.id]
            
          };

            forumsService.createForum(newForum).then(
                (response) => {
                    if(response.status === 201) {
                        this.$router.push({name: "forum", params: {id: response.data.id}})
                    }
                }
            )
        }
    }
}
</script>

<style>
 .cardForm {
    width: 500px;
    padding: 35px;
    margin: 0 auto;
    background-color: #F8F8F8;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.15);
    color: #23468A;
  }
  .cardForm p {
    text-align: center;
    font-size: 24px;
    margin-bottom: 20px;
    color: #23468A;
  }

  .cardForm label {
    display: block;
    font-size: 18px;
    margin-bottom: 10px;
  }
  .cardForm input,
  .cardForm textarea {
    width: 100%;
    padding: 10px;
    font-size: 18px;
    margin-bottom: 20px;
    border-radius: 5px;
    border: none;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.15);
  }

  .cardForm textarea {
    width: 100%;
    padding: 10px;
    font-size: 18px;
    margin-bottom: 20px;
    border-radius: 5px;
    border: none;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    resize: none;
}

.cardForm input[type="submit"] {
    background-color: #23468A;
    color: #EDEDED;
    padding: 10px 20px;
    border-radius: 5px;
    border: none;
    cursor: pointer;
    font-size: 18px;
    margin-right: 20px;
}

.cardForm button[type="cancel"] {
    background-color: #23468A;
    color: #EDEDED;
    padding: 10px 20px;
    border-radius: 5px;
    border: 1px solid #ccc;
    cursor: pointer;
    font-size: 18px;
}

</style>