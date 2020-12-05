import "../../Utils/config";

const URL = `${global.config.BACKEND_URL}/api/shh`;

export const createZone = async (houseId) => {
    try {
        const response = await fetch(
            `${URL}/zone?houseId=${houseId}`,
            {
                headers: {
                    "Content-Type": "application/json",
                },
                method: "POST",
            }
        );
        return response.json()
    } catch (error) {
        throw await error;
    }
}

export const fetchZones = async (houseId) => {
    try {
        const response = await fetch(
            `${URL}/house/${houseId}/zone`,
            {
                headers: {
                    "Content-Type": "application/json",
                },
                method: "GET"
            }
        );
        return response.json()
    } catch (error) {
        throw await error;
    }
}

export const addRoomToZone = async (zoneId, roomId) => {
    try {
        const response = await fetch(
            `${URL}/zone/${zoneId}/room/${roomId}`,
            {
                headers: {
                    "Content-Type": "application/json",
                },
                method: "PUT"
            }
        );
        return response.json()
    } catch (error) {
        throw await error;
    }
}

export const setZoneTemperature = async (zoneId, temperature) => {
    try {
        const response = await fetch(
            `${URL}/zone/${zoneId}?temperature=${temperature}`,
            {
                headers: {
                    "Content-Type": "application/json",
                },
                method: "PUT"
            }
        );
        return response.json()
    } catch (error) {
        throw await error;
    }
}

export const getZoneTemperature = async (zoneId) => {
    try {
        const response = await fetch(
            `${URL}/zone/${zoneId}`,
            {
                headers: {
                    "Content-Type": "application/json",
                },
                method: "GET"
            }
        )
        return response.json()
    } catch (error) {
        throw await error;
    }
}

export const monitorTemperature = async (houseId) => {
    try {
        const response = await fetch(
            `${URL}/monitor/house/${houseId}`,
            {
                headers: {
                    "Content-Type": "application/json",
                },
                method: "GET"
            }
        );
        return response.json()
    } catch (error) {
        throw await error;
    }
}

export const turnOnHAVC = async (houseId) => {
    try {
        await fetch(
            `${URL}/house/${houseId}/havc/on?cityName=Montreal`,
            {
                headers: {
                    "Content-Type": "application/json",
                },
                method: "PUT"
            }
        )
    } catch (error) {
        throw await error;
    }
}

export const turnOffHAVC = async (houseId) => {
    try {
        await fetch(
            `${URL}/house/${houseId}/havc/off?cityName=Montreal`, 
            {
            headers: {
                "Content-Type": "application/json",
            },
            method: "PUT"
        }
        )
    } catch (error) {
        throw await error;
    }
}