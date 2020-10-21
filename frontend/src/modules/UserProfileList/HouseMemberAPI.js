import Axios from "axios"
import "../../Utils/config"

export const fetchHouseMember = async () => {
    try {
        const response = await Axios.get(global.config.BACKEND_URL + `/api/simulation/houseMember/all`)
        return response.data
    } catch (error) {
        throw error.response.data
    }
}

export const fetchRooms = async () => {
    try {
        // Hard coding the house id for now cause we don't have the load house component
        const response = await Axios.get(global.config.BACKEND_URL + `/api/simulation/house/1/room/all`)
        return response.data
    } catch (error) {
        console.log(error.response.data)
        throw error.response.data
    }
}

export const createNewHouseMember = async (name, role, roomId) => {
    try {
        await Axios.post(global.config.BACKEND_URL + `/api/simulation/houseMember`, { name, role, roomId })
    } catch (error) {
        throw error.response.data
    }
}

export const houseMemberNameModification = async (userProfileId, nameField) => {
    try {
        const response = await Axios.put(global.config.BACKEND_URL + `/api/simulation/houseMember/nameModification/${userProfileId}?newName=${nameField}`)
        return response.data
    } catch (error) {
        throw error.response.data
    }
}

export const houseMemberRoleModification = async (userProfileId, roleField) => {
    try {
        const response = await Axios.put(global.config.BACKEND_URL + `/api/simulation/houseMember/roleModification/${userProfileId}?newRole=${roleField}`)
        return response.data
    } catch (error) {
        throw error.response.data
    }
}

export const houseMemberLocationChange = async (roomId, name) => {
    try {
        const response = await Axios.put(global.config.BACKEND_URL + `/api/simulation/room/newRoom/${roomId}?name=${name}`)
        return response.data
    } catch (error) {
        throw error.response.data
    }
}