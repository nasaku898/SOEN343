import Axios from "axios"
import "../../Utils/config"

const URL = global.config.BACKEND_URL;

export const fetchHouseState = async () => {
    try {
        const response = await Axios.get(URL + `/api/simulation/house/1/roomState/all`)
        return response.data
    } catch (error) {
        throw error.response.data
    }
}
export const editRoomTemperature = async (roomId, newTemperature) => {
    try {
        const response = await Axios.put(URL + `/api/simulation/room/${roomId}/temperature/${newTemperature}`)
        return response.data
    } catch (error) {
        throw error.response.data
    }
}

export const updateTemperatureOutside = async (houseId, temperatureOutside) => {
    try {
        const response = await fetch(`${URL}/api/simulation/house/${houseId}/temperature`, {
            headers: {
                "Content-Type": "application/json"
            },
            method: "PUT",
            body: JSON.stringify([houseId, temperatureOutside])
        });
        return response.data
    } catch (error) {
        throw await error.json();
    }
}

export const getRoom = async (roomId) => {
    try {
        const response = await fetch(`${URL}/api/simulation/room/${roomId}`, {
            headers: {
                "Content-Type": "application/json"
            },
        });
        return response.data
    } catch (error) {
        throw await error.json();
    }
}

export const getHouse = async (houseId) => {
    try {
        const response = await fetch(`${URL}/api/${houseId}`, {
            headers: {
                "Content-Type": "application/json"
            },
        });
        return response.data
    } catch (error) {
        throw await error.json();
    }
}