import "../../Utils/config"

const URL = global.config.BACKEND_URL;

export const getUser = async (username) => {
    try {
        const response = await fetch(`${URL}/username`, {
            headers: {
                "Content-Type": "application/json"
            },
        });
        return response.json();
    } catch (error) {
        throw await error.json();
    }
}

export const moveUser = async (username, roomId) => {
    try {
        const response = await fetch(`${URL}/${username}/   room/${roomId}`, {
            headers: {
                "Content-Type": "application/json"
            },
            method: "PUT",
            body: JSON.stringify([username, roomId])
        });
        return response.json();
    } catch (error) {
        throw await error.json();
    }
}