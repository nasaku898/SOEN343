import "../../Utils/config";

const URL = `${global.config.BACKEND_URL}/api/simulation`;

export const findAllRooms = async (houseId) => {
  try {
    const response = await fetch(`${URL}/house/${houseId}/room/all`, {
      headers: {
        "Content-Type": "application/json",
      },
    });
    return response.json();
  } catch (error) {
    throw error.response.data;
  }
};

export const moveHouseMemberToRoom = async (username, roomId) => {
  try {
    const response = await fetch(
      `${URL}/houseMember/${username}/room/${roomId}`,
      {
        headers: {
          "Content-Type": "application/json",
        },
        method: "PUT",
      }
    );
    return response.json();
  } catch (error) {
    throw error.response.data;
  }
};

export const moveUserToRoom = async (username, roomId) => {
  try {
    const response = await fetch(`${URL}/user/${username}/room/${roomId}`, {
      headers: {
        "Content-Type": "application/json",
      },
      method: "PUT",
    });
    return response.json();
  } catch (error) {
    throw error.response.data;
  }
};

export const getCurrentDate = async () => {
  try {
    const response = await fetch(`${URL}/time`, {
      headers: {
        "Content-Type": "application/json",
      },
      method: "GET",
    });
    return response.json();
  } catch (error) {
    throw error.response.data
  }
}

export const setNewDate = async (newTime) => {
  try {
    const response = await fetch(`${URL}/time/${newTime}`, {
      headers: {
        "Content-Type": "application/json",
      },
      method: "PUT",
    });
    return response.json()
  } catch (error) {
    throw error.response.data
  }
}