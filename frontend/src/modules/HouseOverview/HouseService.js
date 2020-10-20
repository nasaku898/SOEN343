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
        const response = await fetch(`${URL}/api/simulation/house/${houseId}/temperatureOutside/${temperatureOutside}`, {
            headers: {
                "Content-Type": "application/json"
            },
            method: "PUT",
        });
        return response.json();
    } catch (error) {
        throw await error.json();
    }
}

export const getRoom = async (id) => {
    try {
        const response = await fetch(`${URL}/api/room/${id}`, {
            headers: {
                "Content-Type": "application/json"
            },
        });
        return response.json();
    } catch (error) {
        throw await error;
    }
}

export const getHouse = async (houseId) => {
    try {
        const response = await fetch(`${URL}/api/simulation/house/houseLayout/${houseId}`, {
            headers: {
                "Content-Type": "application/json"
            },
        });
        return response.json();
    } catch (error) {
        throw await error;
    }
}


