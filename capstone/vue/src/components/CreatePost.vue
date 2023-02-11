<template>
  <div class="create-post">
      
       <h3>Create Post here</h3>
      <!-- <form id="new-post-form"> -->
          <input type="text" placeholder="Post Title" v-model="newPost.title" required />
          <textarea placeholder="Write your post here!" v-model="newPost.body" required></textarea>
          
          <button v-on:click="uploadImage">Upload Image</button>&nbsp;&nbsp;
          <img v-if="newPost.img_url" :src="newPost.img_url" alt="Post Image" />
          
          <button v-on:click="submitPost">Submit</button>
      <!-- </form> -->
  </div>
</template>

<script>
import PostsService from '../services/PostsService';

export default {
    data(){
        return{
            newPost:{
                userId: this.$store.state.user.id,
                forumId: this.$route.params.id,
                title: '',
                body: '',
                img_url: ''
            },
            imageWidget : {}
        }
    },

    methods: {
        uploadImage(){
            this.imageWidget.open()

            
        },


        submitPost(){
            PostsService.createPost(this.newPost).then((response) => {
                if (response.status === 201){
                    this.$router.push({name: 'forum', params:{id: this.$route.params.id}})
                }
            })
        }
    },

    mounted() {
       this.imageWidget = window.cloudinary.createUploadWidget(
      {
        cloudName: 'dmdwwopva', 
        uploadPreset: 'wmfapvvo'
      }, 
      (error, result) => { 
        if (!error && result && result.event === "success") {   
          console.log('Done! Here is the image info: ', result.info); 
          console.log("Image URL: " + result.info.url);
          this.newPost.img_url = result.info.url;
        }
      }
    );

  }

}
</script>

<style scoped>
  .create-post {
  background-color: #fff;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
  margin: 20px auto;
  max-width: 500px;
  padding: 30px;
  text-align: center;
  }

.create-post h3 {
  margin-bottom: 20px;
  font-size: 24px;
  font-weight: bold;
  color: #23468A;
}

  .create-post input[type="text"] {
  width: 100%;
  padding: 10px;
  font-size: 18px;
  margin-bottom: 20px;
  border-radius: 4px;
  border: 1px solid #ddd;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  }

  .create-post textarea {
  width: 100%;
  padding: 10px;
  font-size: 18px;
  margin-bottom: 20px;
  border-radius: 4px;
  border: 1px solid #ddd;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  resize: none;
  }

 .create-post button {
  background-color: #23468A;
  color: #fff;
  padding: 10px 20px;
  border-radius: 4px;
  border: none;
  font-size: 18px;
  font-weight: bold;
  margin-top: 20px;
  cursor: pointer;
  }

  .create-post img {
  width: 100%;
  margin-top: 20px;
  border-radius: 4px;
  }

</style>