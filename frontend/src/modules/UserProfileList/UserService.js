import "../../Utils/config"

const URL = global.config.BACKEND_URL;

export const getUser = async (username) => {
    try {
        const response = await fetch(`${URL}/user/${username}`, {
            headers: {
                "Content-Type": "application/json"
            },
        });
        return response.json();
    } catch (error) {
        throw await error;
    }
}

export const moveUser = async (username, roomId) => {
    try {
        const response = await fetch(`${URL}/user/${username}/room/${roomId}`, {
            headers: {
                "Content-Type": "application/json"
            },
            method: "PUT",
            body: JSON.stringify([username, roomId])
        });
        return response.json();
    } catch (error) {
        throw await error;
    }
}