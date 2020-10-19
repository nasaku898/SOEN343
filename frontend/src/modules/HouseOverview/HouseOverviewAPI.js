import Axios from "axios"
import "../../Utils/config"

export const fetchHouseState = async () => {
    try {
        const response = await Axios.get(global.config.BACKEND_URL + `/api/simulation/house/1/roomState/all`)
        return response.data
    } catch (error) {
        throw error.response.data
    }
}
export const editRoomTemperature = async (roomId, newTemperature) => {
    try {
        const response = await Axios.put(global.config.BACKEND_URL + `/api/simulation/room/${roomId}/temperature/${newTemperature}`)
        return response.data
    } catch (error) {
        throw error.response.data
    }
}