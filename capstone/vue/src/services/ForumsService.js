import axios from 'axios';

export default {

    displayTop5() {
        return axios.get('/forum/top5')
    },

    getAllForums() {
        return axios.get('/forum')
    },

    getForum(id) {
        return axios.get(`/forum/${id}`)
    },

    createForum(Forum) {
        return axios.post('/forum', Forum);
    },
    favoriteForumsByUserId(id) {
        return axios.get(`/forum/user/${id}`)
    }

}