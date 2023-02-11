<template>
<div class="delete-button">
  <button v-on:click="deleteReply(postId, commentId)">Delete</button>
</div>
</template>

<script>
import replyService from "../services/ReplyService";

export default {
  name: "DeleteReply",
  props: {
    reply: Object
  },
  methods: {
    deleteReply(reply) {
  if (confirm("Are you sure you want to delete this reply?")) {
    const commentId = reply.commentId;
    const postId = reply.postId;
    replyService.deleteReply(postId, commentId)
    .then((response) => {
      if (response.status === 204) {
        this.$emit("replyDeleted", { commentId });
      }
    });
  }
}

  }
};
</script>

<style scoped>
.delete-button {
  font-size: 14px;
  cursor: pointer;
  border-radius: 13px;
  width: 70px;
  border: none;
  padding: 7px;
  font-weight: bold;
  cursor: pointer;
  background-color: #23468A;
  color: #f3f3f3;
  margin-top: 50px;
  margin-left: 0vw;
}
.delete-button:hover {
  background-color: #1483d6;
}

</style>


