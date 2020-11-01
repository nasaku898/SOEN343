import Axios from "axios";
import "../../Utils/config";

const URL = global.config.BACKEND_URL;

export const findAllHouseMembers = async (houseId) => {
  try {
    const response = await Axios.get(
      `${URL}/api/simulation/house/${houseId}/houseMember/all`
    );
    return response.data;
  } catch (error) {
    throw error.response.data;
  }
};

export const createNewHouseMember = async (houseMemberDTO) => {
  try {
    const response = await fetch(`${URL}/api/simulation/houseMember`, {
      headers: {
        "Content-Type": "application/json",
      },
      method: "POST",
      body: JSON.stringify(houseMemberDTO),
    });
    return response.json();
  } catch (error) {
    throw await error;
  }
};

export const updateHouseMember = async (houseMemberDTO) => {
  try {
    const response = await fetch(`${URL}/api/simulation/houseMember/update`, {
      headers: {
        "Content-Type": "application/json",
      },
      method: "PUT",
      credentials: "include",
      body: JSON.stringify(houseMemberDTO),
    });
    return response.json();
  } catch (error) {
    throw await error;
  }
};
