import axios from "axios";

export default {
    getFavorites(id) {
        return axios.get(`/favorites/${id}`)
    },
    addFavorite(favorite) {
        return axios.post(`/favorites`, favorite)
    },
    deleteFavorite(favorite) {
        return axios.delete('/deleteFavorites', {data: favorite} )
    }
}