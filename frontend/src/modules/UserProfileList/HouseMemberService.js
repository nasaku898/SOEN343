import Axios from "axios"
import "../../Utils/config"

export const findAllHouseMembers = async () => {
    try {
        const response = await Axios.get(global.config.BACKEND_URL + `/api/simulation/houseMember/all`)
        return response.data
    } catch (error) {
        throw error.response.data
    }
}
export const createNewHouseMember = async (name, role, roomId) => {
    try {
        await Axios.post(global.config.BACKEND_URL + `/api/simulation/houseMember`, {name, role, roomId})
    } catch (error) {
        throw error.response.data
    }
}
