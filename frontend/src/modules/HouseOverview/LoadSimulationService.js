import "../../Utils/config";

export const loadHouseLayout = async (requestBody) => {
  try {
    const response = await fetch(
      global.config.BACKEND_URL + `/api/simulation/houseLayout`,
      {
        headers: {
          "Content-Type": "application/json",
        },
        method: "POST",
        credentials: "include",
        body: JSON.stringify(requestBody),
      }
    );
    return response.json();
  } catch (error) {
    throw error;
  }
};
