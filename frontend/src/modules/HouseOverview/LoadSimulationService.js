import Axios from "axios"
import "../../Utils/config"

export const loadHouseLayout = async (requestBody) => {
    try {
        const response = await Axios.post(global.config.BACKEND_URL + `/api/simulation/houseLayout`, requestBody)
        return response.data
    } catch (error) {
        throw error.response.data
    }
}