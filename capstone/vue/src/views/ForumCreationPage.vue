<template>
<div class="forum-creation">
    <ForumCreationForm />

</div>    

</template>

<script>
import ForumCreationForm from '../components/CreateForum.vue'

export default {
    components: {
        ForumCreationForm
    }

}
</script>

<style>
.forum-creation{
    margin-top: 100px;
}

</style>