import Axios from "axios";
const URL = `${global.config.BACKEND_URL}/api/room/`

export const getRoom = async (id) => {
    try {
        const response = await fetch(`${URL}/${id}`, {
            headers: {
                "Content-Type": "application/json"
            },
        });
        return response.json();
    } catch (error) {
        throw await error;
    }
}

export const editRoomTemperature = async (roomId, newTemperature) => {
    try {
        const response = await Axios.put(`${URL}/${roomId}/temperature/${newTemperature}`)
        return response.data
    } catch (error) {
        throw error.response.data
    }
}

export const getRoomTemperature = async (id) => {
    try {
        const response = await fetch(`${URL}/${id}/temperature`, {
            headers: {
                "Content-Type": "application/json"
            },
        });
        return response.json();
    } catch (error) {
        throw await error;
    }
}