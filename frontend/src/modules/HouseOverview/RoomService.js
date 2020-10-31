import Axios from "axios";
const URL = `${global.config.BACKEND_URL}/api/room`;

export const getRoom = async (id) => {
  try {
    const response = await fetch(`${URL}/${id}`, {
      headers: {
        "Content-Type": "application/json",
      },
    });
    return response.json();
  } catch (error) {
    throw await error;
  }
};

export const editRoomTemperature = async (roomId, newTemperature) => {
  try {
    console.log(roomId);
    console.log(newTemperature);
    const response = await fetch(`${URL}/${roomId}/temperature`, {
      headers: {
        "Content-Type": "application/json",
      },
      method: "PUT",
      body: JSON.stringify(newTemperature),
    });
    return response.json().temperature;
  } catch (error) {
    throw error;
  }
};

export const getRoomTemperature = async (id) => {
  try {
    const response = await fetch(`${URL}/${id}/temperature`, {
      headers: {
        "Content-Type": "application/json",
      },
    });
    return response.json();
  } catch (error) {
    throw await error;
  }
};
