import "../../Utils/config";

const URL = `${global.config.BACKEND_URL}/api/house`;

export const updateTemperatureOutside = async (houseId, temperatureOutside) => {
  try {
    const response = await fetch(
      `${URL}/api/simulation/house/${houseId}/temperatureOutside/${temperatureOutside}`,
      {
        headers: {
          "Content-Type": "application/json",
        },
        method: "PUT",
      }
    );
    return response.json();
  } catch (error) {
    throw await error.json();
  }
};

export const getHouse = async (houseId) => {
  try {
    const response = await fetch(`${URL}/${houseId}`, {
      headers: {
        "Content-Type": "application/json",
      },
    });
    return response.json();
  } catch (error) {
    throw await error;
  }
};

export const modifyLightState = async (lightId, desiredState) => {
  try {
    const response = await fetch(`${URL}/light/${lightId}`, {
      headers: {
        "Content-Type": "application/json",
      },
      method: "PUT",
      body: JSON.stringify(desiredState),
    });
    return response.json();
  } catch (error) {
    throw await error;
  }
};

export const modifyExteriorDoorState = async (doorId, open, desiredState) => {
  const args = {};
  args.open = open;
  args.desiredState = desiredState;
  try {
    const response = await fetch(`${URL}/exteriorDoor/${doorId}`, {
      headers: {
        "Content-Type": "application/json",
      },
      method: "PUT",
      body: JSON.stringify(args),
    });

    if (response.ok) {
      return response.json();
    } else {
      throw new Error("Door is blocked");
    }
  } catch (error) {
    throw await error;
  }
};

export const modifyInteriorDoorState = async (doorId, open) => {
  try {
    const response = await fetch(`${URL}/interiorDoor/${doorId}`, {
      headers: {
        "Content-Type": "application/json",
      },
      method: "PUT",
      body: JSON.stringify(open),
    });
    return response.json();
  } catch (error) {
    throw await error;
  }
};

export const modifyWindowState = async (windowId, open, desiredState) => {
  const args = {};
  args.open = open;
  args.desiredState = desiredState;
  try {
    const response = await fetch(`${URL}/window/${windowId}`, {
      headers: {
        "Content-Type": "application/json",
      },
      method: "PUT",
      body: JSON.stringify(args),
    });
    if (response.ok) {
      return response.json();
    } else {
      throw new Error("Door is blocked");
    }
  } catch (error) {
    throw await error;
  }
};

export const getAllHouses = async () => {
  try {
    const response = await fetch(`${URL}/houses`, {
      headers: {
        "Content-Type": "application/json"
      },
      method: "GET"
    })
    return response.json();
  } catch (error) {
    throw await error
  }
}

export const localStorageHouseID = (houseID)=>{
  localStorage.setItem("houseID", houseID)
}