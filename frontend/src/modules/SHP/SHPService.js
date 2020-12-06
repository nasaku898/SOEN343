import "../../Utils/config";

const URL = `${global.config.BACKEND_URL}/api/security`;

export const createSecurity = async (houseId, away, auto) => {
    try {
        const response = await fetch(
            `${URL}/`,
            {
                headers: {
                    "Content-Type": "application/json",
                },
                method: "POST",
                body: JSON.stringify({ houseId: houseId, away: away, auto: auto })
            }
        )
        return response.json()
    } catch (error) {
        throw await error
    }
}

export const setAwayMode = async (id, desiredState) => {
    try {
        const response = await fetch(
            `${URL}/${id}/away`,
            {
                headers: {
                    "Content-Type": "application/json",
                },
                method: "PUT",
                body: JSON.stringify(desiredState)
            }
        )
        return response.json()
    } catch (error) {
        throw await error
    }
}