import axios from 'axios';


export default {


    getForumPosts(id) {
        return axios.get(`/forum/${id}/posts`)
    },
    getTop10() {
        return axios.get(`/posts/top10`)
    },
    getPost(id) {
        return axios.get(`/posts/${id}`)
    },
    createPost(post) {
        return axios.post(`/posts`, post)
    },
    updatePost(id, post){
        return axios.put(`/posts/${id}`, post)
    },
    uploadPicture(data){
        axios.post(`/post`, data)
    },
    deletePost(id){
        return axios.delete(`/posts/${id}`)
    },
    getForumPostsPopularity(id){
        return axios.get(`forum/${id}/posts/popularity`)
    },
    searchTitleandBodyByKeyword(keyword){
        return axios.get(`/posts/search/${keyword}`)
    }


}