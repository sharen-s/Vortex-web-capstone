import axios from "axios";

export default{
    getModeratorsForForum(forumId){
        return axios.get(`/moderators/forum/${forumId}`)
    },

    addModerator(moderator){
        return axios.post(`/moderators`, moderator)
    },

    getForumsModerated(userId){
        return axios.get(`/moderators/user/${userId}`)
    },

    removeModerator(moderator){
        return axios.delete(`/moderators`, {data: moderator})
    }

}
