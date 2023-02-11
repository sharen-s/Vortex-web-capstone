
<template>
  <div class="add-moderator">
    <label for="user-select" class="add-moderator__label">Select a user: </label>
    <select id="user-select" class="add-moderator__select" v-model="selectedUserId">
      <option v-for="user in users" v-bind:key="user.id" v-bind:value="user.id">
        {{user.username}}
      </option>
    </select>
    <div class="add-mod-button">
    <button class="add-moderator-button" v-on:click="addModerator">Add Moderator</button>
    <p id="message" class="add-moderator__message" v-show="displayMessage">{{message}}</p>
    </div>
  </div>
</template>
<script>
import usersService from "../services/UsersService";
import moderatorsService from "../services/ModeratorsService";

export default {
  data() {
    return {
      users: [],
      selectedUserId: null,
      message: "",
      displayMessage: false
    };
  },
  methods: {
    addModerator() {
      let isModerator = false;
      for (let i = 0; i < this.$store.state.moderatorsForForum.length; i++) {
        let currentObj = this.$store.state.moderatorsForForum[i];
        if (currentObj.moderatorId === this.selectedUserId) {
          isModerator = true;
        }
      }
      if (isModerator) {
        this.message = "User is already a moderator.";
        this.displayMessage = true;
      } else {
        let newModerator = {
          moderatorId: this.selectedUserId,
          forumId: this.$route.params.id
        };
        moderatorsService.addModerator(newModerator).then(response => {
          if (response.status === 201) {
            let index = this.users.findIndex(element => element.id === this.selectedUserId);
            let newModForForum = {
              moderatorId: this.selectedUserId,
              forumId: this.$route.params.id,
              username: this.users[index].username
            };
            this.$store.commit("ADD_MODERATOR", newModForForum);
            this.message = "User added as moderator";
            this.displayMessage = true;
          }
        });
      }
    }
  },
  created() {
    usersService.getAllUsers().then(response => {
      this.users = response.data;
    });
  }
};
</script>
<style>
/* .add-moderator {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
}

.add-moderator__label {
  font-size: 1.2em;
  margin-bottom: 10px;
}

.add-moderator__select {
  width: 300px;
  height: 40px;
  font-size: 1.2em;
  margin-bottom: 20px;
  background-color: #fff;
  border: 1px solid #ccc;
  border-radius: 4px;
  padding: 10px;
}

.add-moderator__button {
  width: 100px;
  height: 40px;
  background-color: #24a0ed;
  color: #fff;
  border-radius: 4px;
  font-size: 1.2em;
  border: none;
  cursor: pointer;
  margin-left: 20px;
}

.add-moderator__message {
  font-size: 1.2em;
  margin-top: 20px;
  color: #24a0ed;
} */
.add-moderator-button:hover{
  background-color: #1483d6;
}
.add-moderator-button{
 border-radius: 13px;
  width: 150px;
  border: none;
  padding: 7px;
  font-weight: bold;
  cursor: pointer; 
  background-color: #23468A;
  color: #f3f3f3;
}
</style>

