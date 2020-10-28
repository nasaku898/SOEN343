import Axios from "axios"
import "../../Utils/config"

const URL = global.config.BACKEND_URL;


export const findAllHouseMembers = async () => {
    try {
        const response = await Axios.get(`${global.config.BACKEND_URL}/api/simulation/houseMember/all`)
        return response.data
    } catch (error) {
        throw error.response.data
    }
}
export const createNewHouseMember = async (houseMember) => {
    try {
        await Axios.post(`${URL}/api/simulation/houseMember`, {houseMember})
    } catch (error) {
        throw error.response.data
    }
}

export const updateHouseMember = async (houseMember) => {
    try {
        const response = await fetch(`${URL}/user/update`, {
            headers: {
                "Content-Type": "application/json"
            },
            method: "PUT",
            body: JSON.stringify(houseMember)
        });
        return response.json();
    } catch (error) {
        throw await error;
    }
}

