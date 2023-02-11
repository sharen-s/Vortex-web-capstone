import axios from 'axios';

export default {

    getPostReplies(id) {
        return axios.get(`/posts/${id}/replies`)
    },
    getReply(id) {
        return axios.get(`/replies/${id}`)
    },
    createReply(reply) {
        return axios.post(`/replies`, reply)
    },
    deleteReply(id) {
        return axios.delete(`/replies/${id}`)
      }
      

}