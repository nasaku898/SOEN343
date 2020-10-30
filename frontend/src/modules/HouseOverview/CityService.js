import "../../Utils/config";

const URL = `${global.config.BACKEND_URL}/api/city`;

export const updateTemperatureOutside = async (name, temperatureOutside) => {
  try {
    const response = await fetch(
      `${URL}/${name}/temperature/${temperatureOutside}`,
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

export const getCity = async (name) => {
  try {
    const response = await fetch(`${URL}/${name}`, {
      headers: {
        "Content-Type": "application/json",
      },
    });
    return response.json();
  } catch (error) {
    throw error.response.data;
  }
};
