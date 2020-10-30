import "../../Utils/config";

const URL = global.config.BACKEND_URL;

export const getUser = async (username) => {
  try {
    const response = await fetch(`${URL}/user/${username}`, {
      headers: {
        "Content-Type": "application/json",
      },
    });
    return response.json();
  } catch (error) {
    throw error;
  }
};

export const moveUser = async (username, roomId) => {
  try {
    const response = await fetch(`${URL}/user/${username}/room/${roomId}`, {
      headers: {
        "Content-Type": "application/json",
      },
      method: "PUT",
      body: JSON.stringify([username, roomId]),
    });
    return response.json();
  } catch (error) {
    throw error;
  }
};

export const getAuthenticatedUser = async () => {
  try {
    const response = await fetch(`${URL}/user`, {
      headers: {
        "Content-Type": "application/json",
      },
      credentials: "include",
    });
    return response.json();
  } catch (error) {
    throw error;
  }
};
