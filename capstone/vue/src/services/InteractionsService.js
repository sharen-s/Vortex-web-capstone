import axios from 'axios';

export default{
    getInteractions(id){
        return axios.get(`/interactions/${id}`)
    },

    addInteraction(interaction){
        return axios.post(`/interactions`, interaction)
    },

    deleteInteraction(interaction){
        return axios.delete(`/deleteInteractions`, {data: interaction})
    }
}